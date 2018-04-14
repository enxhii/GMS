package backend.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(UserDao.class);
	private static final int valid = 1;
	private static final int invalid = 0;
	private static final int checked = 1;
	private static final int unchecked = 0;
	private User user;
	List<Role> list;
	private RoleDao roleDao;

	public User getUser(String username, String password) {
		try {
			String query1 = " select u  from User u  where u.username=:username and u.password=:password and u.status=:valid and u.checked=:checked";
			System.out.println("UserDao class");
			User listuser = entityManager.createQuery(query1, User.class).setParameter("username", username)
					.setParameter("password", password).setParameter("valid", valid).setParameter("checked", checked)
					.getSingleResult();
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

	public User getUserById(Integer id) {
		String query = "select u.id from User u where u.id=?1";
		return entityManager.createQuery(query, User.class).setParameter(1, id).getSingleResult();
	}

	public void addUser(User user, Address address, List<Role> roles) {
		try {
			Customer customer = new Customer();
			entityManager.persist(address);
			logger.info("Address inserted");
			user.setAddress(address);
			user.setRoles(roles);
			user.setStatus(valid);
			user.setChecked(checked);
			entityManager.persist(user);
			logger.info("Role Inserted");
			if (roles == roleDao.getRolesById()) {
				logger.debug(roleDao.getRolesById());
				customer.setUser(user);
				entityManager.persist(customer);
				logger.info("Customer Inserted");
				logger.info("User succesfully registered");
			} else {
				entityManager.persist(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}

	public List<User> listAll() {
		try {
			logger.info("Getting result from user");
			String sql = "select u from User u where u.status=?1";
			List<User> lista = entityManager.createQuery(sql, User.class).setParameter(1, valid).getResultList();
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
			String query = "update User set status=?1 where id=?2";
			Query query2 = entityManager.createQuery(query).setParameter(1, invalid).setParameter(2, id);
			query2.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void updateUser(User user, Address address, List<Role> roles) {
		logger.debug("Address updated");
		user.setAddress(address);
		logger.debug("Roles updated");
		user.setRoles(roles);
		entityManager.refresh(roles);
		entityManager.merge(user);
		entityManager.refresh(user);

		logger.debug("User succesfully  updated");

	}

	public void customerReg(User user, Address address) {
		Role role = new Role();
		Customer customer = new Customer();
		List<Role> roles = new ArrayList<Role>();
		role = roleDao.getRoleById();
		roles.add(role);
		entityManager.persist(address);
		logger.info("Address inserted");
		user.setAddress(address);
		user.setStatus(valid);
		user.setChecked(unchecked);
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

	public void updateProfile(User user, Address address) {
		logger.debug("Inserting new values for user");
		user.setAddress(address);
		logger.debug("Address Done");
		entityManager.merge(user);
		logger.debug("User successfully updated ");

	}

	public List<User> getDisabledUser() {
		try {
			logger.info("Getting result from disabled user");
			String sql = "select u from User u where u.status=?1";
			List<User> lista = entityManager.createQuery(sql, User.class).setParameter(1, invalid).getResultList();
			logger.info("Fetching result from user");
			return lista;
		} catch (Exception exception) {
			logger.debug(exception);
			exception.printStackTrace();
		}
		return null;
	}

	public List<User> getDisabledCustomer() {
		try {
			logger.info("Getting result from disabled customers");
			String sql = "select u from User u where u.checked=?1";
			List<User> lista = entityManager.createQuery(sql, User.class).setParameter(1, unchecked).getResultList();
			logger.info("Fetching result from disabled customer");
			return lista;
		} catch (Exception exception) {
			logger.debug(exception);
			exception.printStackTrace();
		}
		return null;
	}

	public void giveAccess(Integer id) {
		try {
			logger.debug("Giving access to user ");
			String query = "update  User set checked=?1 where id=?2";
			Query query2 = entityManager.createQuery(query).setParameter(1, checked).setParameter(2, id);
			query2.executeUpdate();
			logger.debug("Access Given");
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	public void enableUsers(Integer id) {
		String query = "update User set status=:valid where id=:id";
		Query query2 = entityManager.createQuery(query).setParameter("status", valid).setParameter("id", id);
		query2.executeUpdate();
	}

	public void updatePassword(User user, String password) {
		try {
			logger.debug("Setting new password");
			user.setPassword(password);
			logger.debug("Updating password");
			entityManager.merge(user);
			logger.debug("Password succesfully updated ");
		} catch (Exception e) {
			logger.debug("Error from UserDao class" + e);
		}

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

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
