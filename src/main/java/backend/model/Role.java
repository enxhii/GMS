package backend.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
@Column(name="description")
	private String description;
@Column(name="name")
private String name;
/*
@ManyToMany
@JoinTable(name="role_rights" , joinColumns=@JoinColumn(name="role_id" , referencedColumnName="id"),
inverseJoinColumns=@JoinColumn(name="rights_id", referencedColumnName="id"))
*/
/// private List<Right> rights;
 
	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	


}