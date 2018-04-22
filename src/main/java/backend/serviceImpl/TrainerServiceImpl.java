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
import backend.service.TrainerService;

@Transactional
@Service
public class TrainerServiceImpl implements  TrainerService {
	@Autowired
	private TrainerDao trainerDao;
private List<Programm> programms;
	final static Logger logger = LogManager.getLogger(TrainerServiceImpl.class);

	
	@Override
	public List<Programm> list(int id) {
		programms=trainerDao.list( id);
		return programms;
	}

	

	@Override
	public void addProgramm(Programm programm, User user) {
		try {
			trainerDao.addProgramm(programm, user);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	
	public TrainerDao getTrainerDao() {
		return trainerDao;
	}

	public void setTrainerDao(TrainerDao trainerDao) {
		this.trainerDao = trainerDao;
	}

	public List<Programm> getProgramms() {
		return programms;
	}

	public void setProgramms(List<Programm> programms) {
		this.programms = programms;
	}



	@Override
	public void updateProgram(Programm p) {
trainerDao.updateProgram(p);		
	}



	@Override
	public void deleteProgramm(Integer id) {
		try {
			trainerDao.deleteProgramm(id);		

		} catch (Exception e) {
logger.debug(e);		}
	}



	@Override
	public boolean customerExists(Integer id) {
		return trainerDao.customerExists(id);
	}

}
