package hiber.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "series")
	private int series;

	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private User user;
	
	public Car() {}
	
	public Car(String model, int series) {
		super();
		this.model = model;
		this.series = series;
	}
	
	public String getModel() {
		return model;
	}

	public int getSeries() {
		return series;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "model:" + this.getModel() + ", series:" + this.getSeries();
	}
	
}
