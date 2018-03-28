package backend.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import backend.model.Role;
import backend.model.User;
@Repository
public class RoleDao {
@PersistenceContext
private EntityManager entityManager;
//private static final int valid = 1;
private User user;
private Role role;

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


}
