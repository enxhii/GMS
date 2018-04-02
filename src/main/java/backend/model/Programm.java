package backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programm database table.
 * 
 */
@Entity
@Table(name="programm")
@NamedQuery(name="Programm.findAll", query="SELECT p FROM Programm p")
public class Programm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="burnedCalory")

	private int burnedCalory;

	@Column(name="description")
	private String description;

	@Column(name="duration")
	private String duration;

	@Column(name="header")
	private String header;

	@Column(name="level")
	private String level;

	@Column(name="name")
	private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName="id")  
	private User user;

	//bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy="programms", fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Customer> customers;

	public Programm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBurnedCalory() {
		return this.burnedCalory;
	}

	public void setBurnedCalory(int burnedCalory) {
		this.burnedCalory = burnedCalory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}