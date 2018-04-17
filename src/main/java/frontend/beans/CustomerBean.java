package frontend.beans;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import backend.model.*;
import backend.serviceImpl.*;
import javax.faces.bean.ManagedProperty;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@ManagedBean(name = "customerbean")
@ViewScoped
public class CustomerBean {
	final static Logger logger = LogManager.getLogger(CustomerBean.class);

	@ManagedProperty(value = "#{customerServiceImpl}")
	private CustomerServiceImpl customerService;

	@ManagedProperty(value = "#{trainerServiceImpl}")
	private TrainerServiceImpl trainerService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	private Customer customer;
	private List<Programm> list;
	private Programm programm;
	private Programm selected;

	private List<Programm> selectedProg;

	@PostConstruct
	public void init() {
		selected=new Programm();
		customer = new Customer();
		list = customerService.listProgramms();
		programm = new Programm();
		selectedProg = customerService.listProgramms();

	}

	public boolean attendCourses() {
		try {
		customer.setId(userProfileBean.getUser().getId());
			customerService.add(customer, selectedProg);
			return true;
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;

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

}
