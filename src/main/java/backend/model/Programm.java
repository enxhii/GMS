package backend.model;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the programm database table.
 * 
 */
@Entity
@NamedQuery(name="Programm.findAll", query="SELECT p FROM Programm p")
public class Programm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int burnedCalory;

	private String description;

	private String duration;

	private String header;

	private String level;

	private String name;

	
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

}