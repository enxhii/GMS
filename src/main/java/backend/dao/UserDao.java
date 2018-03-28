package backend.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import backend.model.Address;
import backend.model.Role;
import backend.model.User;

@Repository
public class UserDao {
	@PersistenceContext
	EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(UserDao.class);
	private SessionFactory sessionFactory;

	private static final int valid = 1;
	private User user;

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
			System.out.println("Does Exists method "+ e.getMessage());
			return false;
		}
	}
	public List<Role> findByUser(int id) {
		try {
	    Session session = this.sessionFactory.getCurrentSession();
	    List<Role> roles = session.createQuery("select r from Role r where id=:id").setString("id", "%" + id + "%").list();
	    return roles;
		}catch (Exception e) {
logger.debug(e.getMessage());
return null;
}
	}
	public void addUser(User user, Address address) {
		try {
			entityManager.persist(address);
			entityManager.flush();
			user.setAddress(address);
			entityManager.persist(user);
			entityManager.flush();

		} catch (Exception e) {
			e.getMessage();
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
}
