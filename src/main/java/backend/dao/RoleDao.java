package backend.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import backend.model.Address;
import backend.model.Role;
import backend.model.User;
import backend.model.UserRole;
@Repository
public class RoleDao {
@PersistenceContext
private EntityManager entityManager;
private static final int valid = 1;
private User user;
private Role role;
private UserRole userRole;
public UserRole getUserRole(String username) {
	System.out.println("Roledao???????????");
	 try {				
	       TypedQuery<UserRole> query = entityManager.
	    		   createQuery("select ur.roleId from  UserRole ur , User u  where  ur.userId=u.id and u.username=:username " ,UserRole.class);
	    	    List<UserRole> role = query.getResultList();
                return role.get(0);
	            
	        } catch (NoResultException e) {
	              System.out.println(e.getMessage());
	              System.out.println("Problem from RoleDao class");
	              return null;
	        }	
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
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
public void setUserRole(UserRole userRole) {
	this.userRole = userRole;
}





}
