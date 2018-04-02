package backend.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import backend.model.Address;
import backend.model.Customer;
import backend.model.Role;
import backend.model.User;

@Repository
public class UserDao {
	@PersistenceContext
	EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(UserDao.class);
	private static final int valid = 1;
	private static final int invalid = 0;

	private User user;
	List<Role> list;

	public User getUser(String username, String password) {
		try {
			String query1 = " select u  from User u  where u.username=:username and u.password=:password and u.status=:valid";
			System.out.println("UserDao class");
			User listuser = entityManager.createQuery(query1, User.class).setParameter("username", username)
					.setParameter("password", password).setParameter("valid", valid).getSingleResult();
			System.out.println("Get User method " + listuser);
			return listuser;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Problem from UserDao class");
			return null;
		}
	}

	public boolean doesExists(String username) {

		String query = " select u.username from User u where u.username=?1";
		try {
			System.out.println("Does Exists method ");

			return getEntityManager().createQuery(query).setParameter(1, username).getResultList().stream().findFirst()
					.isPresent();

		} catch (Exception e) {
			System.out.println("Does Exists method " + e.getMessage());
			return false;
		}
	}

	public List<Role> findByUser(int id) {
		try {
			logger.info("Trying to get roles");
			List<Role> roles = entityManager.createQuery("select r from Role r  join fetch r.users u where u.id=:id")
					.setParameter("id", id).getResultList();
			logger.info("Roles retrieved ");
			return roles;
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}

	public Role getRoleById() {
		String query = "select r from Role r where r.name='Member'";
		Role role = entityManager.createQuery(query, Role.class).getSingleResult();
		return role;
	}

	public void addUser(User user, Address address, Customer customer, Role role) {
		try {
			/*
			 * entityManager.persist(address); entityManager.flush();
			 * logger.info("Address inserted"); user.setAddress(address); user.setStatus(0);
			 * Role list=getRoleById(); user.getRoles().add(list); entityManager.flush();
			 * logger.info("Role Inserted"); entityManager.persist(user);
			 * entityManager.flush(); logger.info("User Inserted"); customer.setUser(user);
			 * entityManager.persist(customer); logger.info("Customer Inserted");
			 * logger.info("User succesfully registered");
			 * 
			 */

		} catch (Exception e) {
			e.getMessage();
		}
	}
/*
	public void customerReg(User user, Address address, Customer customer, Role role) {
		
			List<Role> roles = new ArrayList<Role>();
			role = getRoleById();
			roles.add(role);
			entityManager.persist(address);
			entityManager.flush();
			logger.info("Address inserted");
			user.setAddress(address);
			user.setStatus(0);
			
			logger.debug(role);
			logger.debug(role.getName());
			logger.debug(role.getId());
			user.setRoles(roles);
			//entityManager.flush();
			logger.info("Role Inserted");
			customer.setUser(user);
			user.setCustomer(customer);
			entityManager.persist(customer);
			entityManager.flush();
			entityManager.persist(user);

			logger.info("Customer Inserted");
			logger.info("User succesfully registered");

		
	}
*/
	public List<User> listAll() {
		try {
			logger.info("Getting result from user");
			String sql = "select u from User u where u.status=?1";
			List<User> lista = entityManager.createQuery(sql,User.class).setParameter(1, valid).getResultList();
			logger.info("Fetching result from user");
			return lista;
		} catch (Exception exception) {
			logger.debug(exception);
			System.out.println(exception.getMessage());
			System.out.println(exception);
			exception.printStackTrace();
		}			
		return null;

	}

	public void deleteUser(Integer id) {
		try {
			String query="update User set status=?1 where id=?2";
			Query query2=entityManager.createQuery(query).setParameter(1, invalid).setParameter(2, id);
			query2.executeUpdate();
		}catch (Exception e) {
		}
	}
	
	
	
	
	
	public void customerReg(User user, Address address) {
		Role role= new Role();
		Customer customer= new Customer();
		List<Role> roles = new ArrayList<Role>();
		role = getRoleById();
		roles.add(role);
		entityManager.persist(address);
		logger.info("Address inserted");
		user.setAddress(address);
		user.setStatus(0);
		logger.debug(role);
		logger.debug(role.getName());
		logger.debug(role.getId());
		user.setRoles(roles);

		entityManager.persist(user);
		logger.info("Role Inserted");
		customer.setUser(user);
		entityManager.persist(customer);
		logger.info("Customer Inserted");
		logger.info("User succesfully registered");

	
}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
