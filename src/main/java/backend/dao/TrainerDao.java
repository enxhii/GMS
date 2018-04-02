package backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import backend.model.Programm;
import backend.model.User;
@Repository
public class TrainerDao {
	@PersistenceContext
	private EntityManager entityManager;
	final static Logger logger = LogManager.getLogger(TrainerDao.class);

	public void addProgramm(Programm programm, User user) {
		try {
			programm.setUser(user);
			entityManager.persist(programm);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Programm> list(){
		String query="select p from Programm p";
		List<Programm> lista =entityManager.createQuery(query, Programm.class).getResultList();
		return lista;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
