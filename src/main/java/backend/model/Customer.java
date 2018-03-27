package backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double height;

	private double length;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="id")
	private User user;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}