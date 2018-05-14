package backend.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import backend.model.Customer;
import backend.model.Programm;
import backend.model.User;

@Repository
public class TrainerDao {
	@PersistenceContext
	private EntityManager entityManager;
	final static Logger LOGGER = LogManager.getLogger(TrainerDao.class);

	public void addProgramm(Programm programm, User user) {
		try {
			LOGGER.debug("Setting user id");
			programm.setUser(user);
			programm.setStatus(1);
			entityManager.persist(programm);
			LOGGER.debug("Program Succesfully added");
		} catch (Exception e) {
			LOGGER.debug(e);
			e.printStackTrace();
		}
	}

	public boolean customerExists(Integer id) {
		String query = "select c from Customer c join c.programms p where p.id=?1 ";
		LOGGER.debug("Checking if any customer exists");
		@SuppressWarnings("unchecked")
		List<Customer> list = entityManager.createQuery(query).setParameter(1, id).getResultList();
		LOGGER.debug(entityManager.createQuery(query).setParameter(1, id).getResultList());
		if (!list.isEmpty()) {
			LOGGER.debug("List is not empty");
			LOGGER.debug(list.isEmpty());
			return true;
		} else if (list.isEmpty()) {
			LOGGER.debug("List is empty");
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
			LOGGER.debug(e);
		}
	}

	public Programm getProgramm(Integer id) {
		try {
			String query = "select p from  Programm p  where id=?1";
			Programm programm = entityManager.createQuery(query, Programm.class).setParameter(1, id).getSingleResult();
			LOGGER.debug("Dao method getProgramID" + programm);
			return programm;
		} catch (Exception e) {
			LOGGER.debug("Problem from Dao method getProgramID");
			e.printStackTrace();
			LOGGER.debug(e);
		}
		return null;
	}

	public void updateProgram(Programm p) {
		try {
			entityManager.merge(p);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}

	public List<Programm> list(int id) {
		try {
			String query = "select p from Programm p   where p.user.id=?1 and p.status=?2";
			List<Programm> lista = entityManager.createQuery(query, Programm.class).setParameter(1, id)
					.setParameter(2, 1).getResultList();
			return lista;
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return Collections.emptyList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
