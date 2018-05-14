package frontend.beans;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import backend.model.Customer;
import backend.model.Programm;
import backend.model.User;
import backend.serviceImpl.CustomerServiceImpl;
import backend.serviceImpl.TrainerServiceImpl;

@ManagedBean(name = "customerbean")
@ViewScoped
public class CustomerBean {
	final static Logger LOGGER = LogManager.getLogger(CustomerBean.class);
	@ManagedProperty(value = "#{customerServiceImpl}")
	private CustomerServiceImpl customerService;

	@ManagedProperty(value = "#{trainerServiceImpl}")
	private TrainerServiceImpl trainerService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;
	private static final String PF_ADDP_DIALOG_HIDE = "PF('ADialog').hide()";

	private Customer customer;
	private List<Programm> list;
	private Programm programm;
	private Programm selected;
	private User user;
	private List<Programm> selectedProg;

	@PostConstruct
	public void init() {
		selected = new Programm();
		list = customerService.listProgramms();
		programm = new Programm();
		customer = new Customer();
	}

	public void attendCourses() {
		try {
			customer = customerService.getCustomer(userProfileBean.getUser().getId());
			addMessage("You are now enrolled ");
			executeScript(PF_ADDP_DIALOG_HIDE);
			customerService.add(customer, selectedProg);
			LOGGER.debug("Bean Customer " + customer.getId());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e);
		}

	}

	public List<Programm> displayProgramm() {
		list = customerService.listProgramms();
		return list;
	}

	public Programm getProgramm(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id not null");
		}
		Optional<Programm> optional = list.stream().filter(programm -> id.equals(programm.getId())).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
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

	public TrainerServiceImpl getTrainerService() {
		return trainerService;
	}

	public void setTrainerService(TrainerServiceImpl trainerService) {
		this.trainerService = trainerService;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Programm> getList() {
		return list;
	}

	public void setList(List<Programm> list) {
		this.list = list;
	}

	public CustomerServiceImpl getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}

	public Programm getProgramm() {
		return programm;
	}

	public void setProgramm(Programm programm) {
		this.programm = programm;
	}

	public List<Programm> getSelectedProg() {
		return selectedProg;
	}

	public void setSelectedProg(List<Programm> selectedProg) {
		this.selectedProg = selectedProg;
	}

	public Programm getSelected() {
		return selected;
	}

	public void setSelected(Programm selected) {
		this.selected = selected;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
