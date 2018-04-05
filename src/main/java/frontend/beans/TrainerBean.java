package frontend.beans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import backend.dao.TrainerDao;
import backend.model.Programm;
import backend.model.User;
import backend.service.GenericService;
import backend.service.TrainerService;

@ManagedBean
@ViewScoped
public class TrainerBean {
@ManagedProperty(value="#{trainerServiceImpl}")
private TrainerService trainerService;
@ManagedProperty(value="#{genericServiceImpl}")
private GenericService<Programm>  genericService;

@ManagedProperty(value = "#{userProfileBean}")
private UserProfileBean userProfileBean;

@Autowired
private TrainerDao trainerDao;

private Programm programm;
private User user;
private List<Programm> lista;
@PostConstruct
public void init() {
	user=new User();
	programm= new Programm();
	lista=trainerService.list();
}
public void addProgramm() {
	user.setId(userProfileBean.getUser().getId());
	trainerService.addProgramm(programm, user);
	
}
public String updateprogramm() {
	trainerService.addProgramm(programm, user);
	return null;
}
public List<Programm> getProgramms(){
	lista=genericService.listAll();
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
}
