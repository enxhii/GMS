package backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import backend.model.Customer;
import backend.model.Programm;
import backend.model.User;

@Repository
public class TrainerDao {
	@PersistenceContext
	private EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(TrainerDao.class);

	public void addProgramm(Programm programm, User user) {
		try {
			logger.debug("Setting user id");
			programm.setUser(user);
			programm.setStatus(1);
			entityManager.persist(programm);
			logger.debug("Program Succesfully added");
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
	}

	public boolean customerExists(Integer id) {
		String query = "select c from Customer c join c.programms p where p.id=?1 ";
		logger.debug("Checking if any customer exists");
		List<Customer> list = entityManager.createQuery(query).setParameter(1, id).getResultList();
		logger.debug(entityManager.createQuery(query).setParameter(1, id).getResultList());
		if (!list.isEmpty()) {
			logger.debug("List is not empty");
			logger.debug(list.isEmpty());
			return true;
		} else if (list.isEmpty()) {
			logger.debug("List is empty");
			return false;
		}
		return false;

	}

	public void deleteProgramm(Integer id) {
		try {
			String query = "update Programm set status=?1 where id=?2";
			Query query2 = entityManager.createQuery(query).setParameter(1, 0).setParameter(2, id);
			query2.executeUpdate();
		} catch (Exception e) {
		}
	}

	public void updateProgram(Programm p) {
		try {
			entityManager.merge(p);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	public List<Programm> list(int id) {
		String query = "select p from Programm p   where p.user.id=?1 and p.status=?2";
		List<Programm> lista = entityManager.createQuery(query, Programm.class).setParameter(1, id).setParameter(2, 1)
				.getResultList();
		return lista;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
