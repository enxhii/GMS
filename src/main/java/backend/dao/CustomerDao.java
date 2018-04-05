package backend.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import backend.model.Customer;
import backend.model.Programm;
@Repository
public class CustomerDao {
@PersistenceContext
private EntityManager entityManager;
	private TrainerDao trainerDao;
	
	public boolean chooseProgram(Customer customer,Programm programm) {
		List<Programm> list=trainerDao.list();
		list.add(programm);
		customer.setProgramms(list);
		entityManager.persist(customer);
		return true;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public TrainerDao getTrainerDao() {
		return trainerDao;
	}


	public void setTrainerDao(TrainerDao trainerDao) {
		this.trainerDao = trainerDao;
	}
	
	
	
	
	
	
}
