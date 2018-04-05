package frontend.beans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import backend.model.*;
import backend.serviceImpl.*;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "customerbean")
@ViewScoped
public class CustomerBean {
	
	@ManagedProperty(value = "#{customerServiceImpl}")
	private CustomerServiceImpl customerService;
	
	@ManagedProperty(value = "#{trainerServiceImpl}")
	private TrainerServiceImpl trainerService;
	
	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;
	
	private Customer customer;
	private List<Programm> list;
private Programm programm;
	@PostConstruct
	public void init() {
		customer = new Customer();
		list = trainerService.list();
		programm=new Programm();
				
	}

	public boolean attendCourses() {
		customer.setId(userProfileBean.getUser().getId());
		customerService.add(customer,programm);
		return true;

	}

	public List<Programm> displayProgramm() {
		list = trainerService.list();
		return list;
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


	
}
