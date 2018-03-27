package backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import backend.model.User;
@Repository
public class LoginDao {
	@PersistenceContext
	EntityManager entityManager;
		
	private User user;
	public User getUser(String username, String password) {
		   
        try {
       @SuppressWarnings("unused")
       TypedQuery<User> query = entityManager.createQuery("from User where username=?1 and password=?2" ,User.class)
       .setParameter(1, username).setParameter(2,password);
    	    List<User> emps = query.getResultList();
	return emps.get(0);
            
        } catch (NoResultException e) {
              System.out.println(e.getMessage());
              System.out.println("Problem from UserDao class");
              return null;
        }
  }
	public boolean doesExists(String username) {
		

			String query = "select id from UserEntity where username= :username";
try {
	@SuppressWarnings("unused")
	User user=(User) entityManager.createQuery(query).
			setParameter(1, username).getResultList();
	return true;
}
catch(NoResultException e){
	e.getMessage();
	return false;
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
