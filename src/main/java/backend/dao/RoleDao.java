package backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import backend.model.Role;
import backend.model.User;

@Repository
public class RoleDao {
	@PersistenceContext
	private EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(RoleDao.class);
	private User user;
	private Role role;

	@SuppressWarnings("unchecked")
	public List<Role> listAll() {
		try {
			logger.debug("Getting result from roles");
			String sql = "SELECT r FROM Role r";
			logger.debug("Fetching result from roles");
			List<Role> list = entityManager.createQuery(sql).getResultList();
			return list;
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return null;

	}

	public Role getRoleById() {
		String query = "select r from Role r where r.name='Member'";
		Role role = entityManager.createQuery(query, Role.class).getSingleResult();
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRolesA(int id) {
		try {
			String query = "select r.name from Role r join r.users u where u.id=?1";
			return getEntityManager().createQuery(query).setParameter(1, id).getResultList();
		} catch (Exception e) {
			logger.debug(e);
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
