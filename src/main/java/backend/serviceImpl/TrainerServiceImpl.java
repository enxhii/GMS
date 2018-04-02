package backend.serviceImpl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.dao.TrainerDao;
import backend.model.Programm;
import backend.model.User;
import backend.service.GenericService;
import backend.service.TrainerService;

@Transactional
@Service
public class TrainerServiceImpl implements GenericService<Programm>, TrainerService {
	@Autowired
	private TrainerDao trainerDao;

	final static Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

	@Override
	public void update(Programm entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Programm entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Programm getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programm> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int entityId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProgramm(Programm programm, User user) {
		try {
			trainerDao.addProgramm(programm, user);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	@Override
	public void add(Programm entity) {
		// TODO Auto-generated method stub

	}

	public TrainerDao getTrainerDao() {
		return trainerDao;
	}

	public void setTrainerDao(TrainerDao trainerDao) {
		this.trainerDao = trainerDao;
	}

}
