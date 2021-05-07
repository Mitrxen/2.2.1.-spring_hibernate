package hiber.dao;

import hiber.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(User user) {
	   sessionFactory.getCurrentSession().save(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
	   TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
	}

	@Override
	public User getUserByCar(String model, int series) {
		TypedQuery<User> query = sessionFactory.getCurrentSession()
				.createQuery("FROM User user LEFT JOIN user.car car WHERE car.model = :model AND car.series = :series")
					.setParameter("model", model)
					.setParameter("series", series);
		List<User> allUsers = query.getResultList();
		return CollectionUtils.isEmpty(allUsers) ? null : allUsers.get(0);
	}

}
