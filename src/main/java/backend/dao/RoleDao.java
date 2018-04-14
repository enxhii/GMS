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
	public List<Role> getRolesById() {
		String query = "select r from Role r where r.name='Member'";
		List<Role> role = entityManager.createQuery(query, Role.class).getResultList();
		return role;
	}
	public List<Role> getUserRoles(int id) {
		try {
			logger.info("Getting result from users and roles ");
			String query = "SELECT r from Role r join r.users u where u.id=:id ";
			List<Role> lista = entityManager.createQuery(query).setParameter("id", id).getResultList();
			logger.info("Fetching result from user");
			logger.debug(lista);
			return lista;
		} catch (Exception exception) {
			logger.debug(exception);
			exception.printStackTrace();
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
