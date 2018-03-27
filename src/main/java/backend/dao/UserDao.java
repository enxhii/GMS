package backend.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import backend.model.Address;
import backend.model.User;
import backend.model.UserRole;
@Repository
public class UserDao {
	@PersistenceContext
	EntityManager entityManager;

	private static final int valid = 1;
	private User user;
	private UserRole userRole;
	public User getUser(String username, String password) {
		   
        try {
       TypedQuery<User> query = entityManager.createQuery("from User where username=?1 ,status=?2 and password=?3" ,User.class)
       .setParameter(1, username).setParameter(2,valid).setParameter(3, password);
    	    List<User> emps = query.getResultList();
	           return emps.get(0);
            
        } catch (NoResultException e) {
              System.out.println(e.getMessage());
              System.out.println("Problem from UserDao class");
              return null;
        }
  }
	public boolean doesExists(String username) {
		

			String query = " from User where username=?1";
try {
	return getEntityManager()
	.createQuery(query)
	.setParameter(1, username)
	.getResultList().stream()
	.findFirst().isPresent();
	
}
catch(NoResultException e){
	e.getMessage();
	return false;
}
	}

	public void addUser(User user , Address address,UserRole userRole) {
		try {
			entityManager.persist(address);
			entityManager.flush();
			user.setAddress(address);
			entityManager.persist(user);
			entityManager.flush();
			entityManager.persist(userRole);
			
		}catch (Exception e) {
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
