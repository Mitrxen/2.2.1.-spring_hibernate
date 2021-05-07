package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      
      
      Car car1 = new Car("Auto1", 1980);
      Car car2 = new Car("Auto2", 1990);
      
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", new Car("Auto3", 2000));
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car2);
      
      userService.add(new User("User5", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User6", "Lastname2", "user2@mail.ru", new Car("Auto4", 2010)));
      userService.add(user3);
      userService.add(user4);

      
      
      
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ (user.getCar() == null ? "No car" : user.getCar()));
         System.out.println();
         
      }
      
      System.out.println(userService.getUserByCar("User0", 2000));
      System.out.println(userService.getUserByCar("Auto3", 2000));
      context.close();
   }
}
