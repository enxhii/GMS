package backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cust_attends_prog database table.
 * 
 */
@Entity
@Table(name="cust_attends_prog")
@NamedQuery(name="CustAttendsProg.findAll", query="SELECT c FROM CustAttendsProg c")
public class CustAttendsProg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="customer_id")
	private int customerId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="program_id")
	private int programId;

	public CustAttendsProg() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getProgramId() {
		return this.programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

}