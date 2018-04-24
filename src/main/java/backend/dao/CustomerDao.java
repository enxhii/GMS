package backend.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import backend.model.Customer;
import backend.model.Programm;
import backend.model.User;

@Repository
public class CustomerDao {
	final static Logger logger = LogManager.getLogger(CustomerDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	private User user;

	public List<Programm> listProgramms() {
		try {
			String query = "select p from Programm p";
			logger.debug("Getting list of programms");
			List<Programm> lista = entityManager.createQuery(query, Programm.class).getResultList();
			System.out.println(lista);
			logger.debug(lista);
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
		return null;

	}

	public boolean chooseProgram(Customer customer, List<Programm> programm) {
		try {
			logger.debug("Getting programss");
			customer.setProgramms(programm);
			logger.debug("DaoMethod" + customer.getId());
			logger.debug(customer.toString());
			logger.debug(customer);
			logger.debug("Inserting programs");
			entityManager.merge(customer);
			logger.debug("DaoMethod" + customer.getId());
			logger.debug("Inserting customers");
			return true;
		} catch (Exception e) {
			logger.debug("Choose Program failed" +e);
			e.printStackTrace();
		}
		return false;
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

}
