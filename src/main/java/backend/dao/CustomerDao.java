package backend.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import backend.encryption.PasswordEncryptor;
import backend.model.Address;
import backend.model.Customer;
import backend.model.Programm;
import backend.model.Role;
import backend.model.User;

@Repository
public class CustomerDao {
	final static Logger LOGGER = LogManager.getLogger(CustomerDao.class);
	private static final int VALID = 1;
	private static final int UNCHECKED = 0;

	@PersistenceContext
	private EntityManager entityManager;
	private User user;

	public List<Programm> listProgramms() {
		try {
			String query = "select p from Programm p";
			LOGGER.debug("Getting list of programms");
			List<Programm> lista = entityManager.createQuery(query, Programm.class).getResultList();
			LOGGER.debug(lista);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e);
		}
		return null;

	}

	public void chooseProgram(Customer customer, List<Programm> programm) {
		try {
			customer.setProgramms(programm);
			LOGGER.debug(programm);
			LOGGER.debug("DaoMethod" + customer.getId());
			LOGGER.debug("Inserting programs....");
			entityManager.merge(customer);
			LOGGER.debug("DaoMethod" + customer.getId());
			LOGGER.debug("Inserting customers...");
			LOGGER.debug("Success");

		} catch (Exception e) {
			LOGGER.debug("Choose Program failed" + e);
			e.printStackTrace();
		}
	}

	public void customerReg(User user, Address address, ArrayList<Role> roles) {
		try {
			Customer customer = new Customer();
			user.setPassword(PasswordEncryptor.encrypt(user.getPassword()));
			entityManager.persist(address);
			LOGGER.info("Address inserted");
			user.setAddress(address);
			user.setStatus(VALID);
			user.setChecked(UNCHECKED);
			user.setRoles(roles);
			entityManager.persist(user);
			LOGGER.info("Role Inserted");
			customer.setUser(user);
			entityManager.persist(customer);
			LOGGER.info("Customer Inserted");
			LOGGER.info("User succesfully registered");
		} catch (Exception e) {
			LOGGER.debug("UserDao problem customeReg method " + e);
		}
	}

	public Customer getCustomer(int id) {
		try {
			String sql = "select c from Customer c  join c.user u where u.id=?1";
			LOGGER.debug(entityManager.createQuery(sql, Customer.class).setParameter(1, id).getFirstResult());
			Customer customer = entityManager.createQuery(sql, Customer.class).setParameter(1, id).getSingleResult();
			return customer;
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
