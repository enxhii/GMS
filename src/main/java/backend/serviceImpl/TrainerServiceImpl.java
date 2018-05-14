package backend.serviceImpl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dao.CategoryDao;
import backend.dao.TrainerDao;
import backend.model.Programm;
import backend.model.User;
import backend.service.TrainerService;

@Transactional
@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	private TrainerDao trainerDao;
	@Autowired
	private CategoryDao categoryDao;
	private List<Programm> programms;
	final static Logger LOGGER = LogManager.getLogger(TrainerServiceImpl.class);

	@Override
	public List<Programm> list(int id) {
		programms = trainerDao.list(id);
		return programms;
	}

	@Override
	public void addProgramm(Programm programm, User user) {
		try {
			trainerDao.addProgramm(programm, user);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
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
			LOGGER.debug(e);
		}
	}

	@Override
	public boolean customerExists(Integer id) {
		return trainerDao.customerExists(id);
	}

	@Override
	public Programm getProgramm(Integer id) {
		return trainerDao.getProgramm(id);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
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

}
