package frontend.beans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import backend.dao.TrainerDao;
import backend.model.Programm;
import backend.model.User;
import backend.service.GenericService;
import backend.service.TrainerService;

@ManagedBean
@ViewScoped
public class TrainerBean {
	final static Logger logger = LogManager.getLogger(TrainerBean.class);

	@ManagedProperty(value = "#{trainerServiceImpl}")
	private TrainerService trainerService;

	@ManagedProperty(value = "#{genericServiceImpl}")
	private GenericService<Programm> genericService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	@Autowired
	private TrainerDao trainerDao;

	private Programm programm;
	private User user;
	private List<Programm> lista;
	private Programm deletep;
	private Programm update;

	@PostConstruct
	public void init() {
		user = new User();
		programm = new Programm();
		update=new Programm();
		lista = trainerService.list(userProfileBean.getUser().getId());
	}

	public void addProgramm() {
		try {
			user.setId(userProfileBean.getUser().getId());
			trainerService.addProgramm(programm, user);
		} catch (Exception e) {
			logger.debug(e);
		}

	}

	public void deleteProgramm() {
		lista.remove(deletep);
		trainerService.deleteProgramm(deletep.getId());
	}

	public String updateprogramm() {
		trainerService.updateProgram(update);
		return "";
	}

	public List<Programm> getProgramms() {
		lista = trainerService.list(userProfileBean.getUser().getId());
		return lista;
	}

	public TrainerService getTrainerService() {
		return trainerService;
	}

	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	public GenericService<Programm> getGenericService() {
		return genericService;
	}

	public void setGenericService(GenericService<Programm> genericService) {
		this.genericService = genericService;
	}

	public TrainerDao getTrainerDao() {
		return trainerDao;
	}

	public void setTrainerDao(TrainerDao trainerDao) {
		this.trainerDao = trainerDao;
	}

	public Programm getProgramm() {
		return programm;
	}

	public void setProgramm(Programm programm) {
		this.programm = programm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Programm> getLista() {
		return lista;
	}

	public void setLista(List<Programm> lista) {
		this.lista = lista;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public Programm getDeletep() {
		return deletep;
	}

	public void setDeletep(Programm deletep) {
		this.deletep = deletep;
	}

	public Programm getUpdate() {
		return update;
	}

	public void setUpdate(Programm update) {
		this.update = update;
	}

}
