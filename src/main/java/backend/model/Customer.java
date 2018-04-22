package backend.model;

import java.io.Serializable;
import javax.persistence.*;
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
	private Integer id;

	// bi-directional one-to-one association to User
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private User user;

	// bi-directional many-to-many association to Programm
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "cust_attends_prog", joinColumns = @JoinColumn(name = "customer_id" , 
	referencedColumnName = "id", nullable = false, insertable = true), inverseJoinColumns = @JoinColumn(name = "program_id", referencedColumnName = "id"))

	private List<Programm> programms;

	public Customer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

}