package frontend.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import backend.model.Address;
import backend.model.User;
import backend.service.CustomerService;
import backend.service.UserService;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {
	@ManagedProperty(value = "#{customerServiceImpl}")
	private CustomerService customerService;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	private User user;
	private String confirmPass;
	private Address address;

	@PostConstruct
	public void init() {
		user = new User();
		address = new Address();

	}

	public User addCustomer() throws IOException {
		if (!userService.doesExists(user.getUsername())) {
			customerService.customerReg(user, address);
			addMessage(user.getUsername() + "  " + "You are now registered!" + "You will login to your account"
					+ " after activation !" + " ");
			user = new User();
			address = new Address();
		} else {
			addMessage("This username is taken!Choose another one .");
		}
		return user;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		requestFacesContext().addMessage(null, message);
	}

	private FacesContext requestFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@SuppressWarnings("unused")
	private RequestContext requestContext() {
		return RequestContext.getCurrentInstance();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
