package frontend.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
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
	private static final String PF_AddProgrammDialog_HIDE = "PF('AddProgrammDialog').hide()";
	private static final String PF_EditProgrammDialog_HIDE = "PF('EditProgrammDialog').hide()";

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
	private List<String> levels;

	@PostConstruct
	public void init() {
		user = new User();
		programm = new Programm();
		update = new Programm();
		lista = trainerService.list(userProfileBean.getUser().getId());
		levels = new ArrayList<>();
		levels.add("Easy");
		levels.add("Medium");
		levels.add("Advanced ");
	}

	public void addProgramm() {
		try {
			user.setId(userProfileBean.getUser().getId());
			trainerService.addProgramm(programm, user);
			addMessage("Programm added succesfully");
			executeScript(PF_AddProgrammDialog_HIDE);
		} catch (Exception e) {
			logger.debug(e);
		}

	}

	public void deleteProgramm() {
		logger.debug(trainerService.customerExists(deletep.getId()));
		if (trainerService.customerExists(deletep.getId()) == false) {
			logger.debug(deletep.getId());
			lista.remove(deletep);
			trainerService.deleteProgramm(deletep.getId());
			addMessage("Programm deleted succesfully");
		} else {
			addMessage("Programm failed to delete .Customers are still registered !");

		}

	}

	public String updateprogramm() {
		trainerService.updateProgram(update);
		addMessage("Programm updated succesfully");
		executeScript(PF_EditProgrammDialog_HIDE);
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

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		requestFacesContext().addMessage(null, message);
	}

	private FacesContext requestFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private RequestContext requestContext() {
		return RequestContext.getCurrentInstance();
	}

	private void executeScript(String script) {
		requestContext().execute(script);
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(List<String> levels) {
		this.levels = levels;
	}
}
