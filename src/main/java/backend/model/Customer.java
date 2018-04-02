package backend.model;

import java.io.Serializable;
import javax.persistence.*;

import com.sun.faces.context.flash.FlashELResolver;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.List;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name = "customer")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
		// bi-directional one-to-one association to User
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name ="customer_id" )
	private User user;
	//private int customer_id;
	

	// bi-directional many-to-many association to Programm
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cust_attends_prog", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "program_id", referencedColumnName = "id", nullable = false))

	private List<Programm> programms;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Programm> getProgramms() {
		return this.programms;
	}

	public void setProgramms(List<Programm> programms) {
		this.programms = programms;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
/*
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
*/
}