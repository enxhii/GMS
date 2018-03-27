package backend.model;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@Column(name="id")
	private int id;

	//@Column(name="email")
	private String email;

	//@Column(name="gender")
	private String gender;
	
	//@Column(name="name")
	private String name;
	
	//@Column(name="password")
	private String password;
	
	//@Column(name="phone")
	private String phone;
	
	//@Column(name="surname")
	private String surname;
	
	//@Column(name="username")
	private String username;
	
	//@Column(name="status")
private Integer status ;
	//bi-directional one-to-one association to Customer
	@OneToOne(mappedBy="user")
	private Customer customer;;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="adresa_id")
	private Address address;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}