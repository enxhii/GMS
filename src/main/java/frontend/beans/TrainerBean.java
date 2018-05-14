package frontend.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import backend.model.Category;
import backend.model.Programm;
import backend.model.User;
import backend.service.CategoryService;
import backend.service.TrainerService;

@ManagedBean
@ViewScoped
public class TrainerBean {
	final static Logger LOGGER = LogManager.getLogger(TrainerBean.class);
	private static final String PF_AddProgrammDialog_HIDE = "PF('AddProgrammDialog').hide()";
	private static final String PF_EditProgrammDialog_HIDE = "PF('EditProgrammDialog').hide()";
	private static final String PF_AddCategoryDialog_HIDE = "PF('AddCategoryDialog').hide()";

	private ExternalContext externalContext;

	@ManagedProperty(value = "#{trainerServiceImpl}")
	private TrainerService trainerService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	@ManagedProperty(value = "#{categoryServiceImpl}")
	private CategoryService categoryService;

	private Programm programm;
	private User user;
	private List<Programm> lista;
	private Programm deletep;
	private Programm update;
	private List<String> levels;
	private List<Category> categories;
	private Category category;
	private Category categoryAdd;

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
		categories = categoryService.getCategory();
		categoryAdd = new Category();
	}

	public void addProgramm() {
		try {
			user.setId(userProfileBean.getUser().getId());
			trainerService.addProgramm(programm, user);
			addMessage("Programm " + "  " + programm.getName() + "  " + " added succesfully");
			executeScript(PF_AddProgrammDialog_HIDE);
		} catch (Exception e) {
			LOGGER.debug(e);
		}

	}

	public void addCategory() {
		categoryService.addCategory(categoryAdd);
		addMessage("Category  " + " " + categoryAdd.getName() + "   " + "Added  succesfully");
		executeScript(PF_AddCategoryDialog_HIDE);
	}

	public void deleteProgramm() {
		LOGGER.debug(trainerService.customerExists(deletep.getId()));
		if (trainerService.customerExists(deletep.getId()) == false) {
			LOGGER.debug(deletep.getId());
			lista.remove(deletep);
			trainerService.deleteProgramm(deletep.getId());
			addMessage("Programm " + deletep.getName() + " " + " deleted succesfully");
		} else {
			addMessage("Programm failed to delete .Customers are still registered !");

		}

	}

	public void updateprogramm() {
		trainerService.updateProgram(update);
		addMessage("Programm updated succesfully");
		executeScript(PF_EditProgrammDialog_HIDE);
	}

	public void displayProgram() throws IOException {
		trainerService.getProgramm(update.getId());
		RequestContext.getCurrentInstance().execute("window.open(href ='../trainer/programminfo.xhtml ')");

	}

	public Category getCategory(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id not null");
		}
		Optional<Category> optional = categories.stream().filter(category -> id.equals(category.getId())).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
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

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public void setExternalContext(ExternalContext externalContext) {
		this.externalContext = externalContext;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getCategoryAdd() {
		return categoryAdd;
	}

	public void setCategoryAdd(Category categoryAdd) {
		this.categoryAdd = categoryAdd;
	}

}
