package frontend.beans;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import backend.SessionUtils.SessionUtils;
import backend.model.Role;
import backend.model.User;
import backend.service.UserService;

@ManagedBean(name = "login")
@RequestScoped
public class LoginManagedBean {
	private String logout;
	private String username;
	private String password;
	private String confirmPass;
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	
	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;
	
	private User user;
	private Role role;
	private List<User> userlist;
	private List<Role> listRole;
	final static Logger logger = LogManager.getLogger(LoginManagedBean.class);
private UserType type;
ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

	public void submit() throws IOException {

		try {
			if (userService.doesExists(username)) {

				logger.debug("Checking if user exists ");
				user = userService.getUser(username, password);
				//HttpSession session = SessionUtils.getSession();
				//session.setAttribute("id", user.getId());
				//session.setAttribute("username", user.getUsername());
				logger.debug("User Found");	
				userProfileBean.setUser(user);

			if(!user.getRoles().isEmpty()) {
				externalContext.redirect(externalContext.getRequestContextPath() + "/admin/admin.xhtml");
				logger.debug("User with username " + user.getUsername() + "logged in");
			}
			
			} else {
				
				logger.debug("User doesn't exists");
				externalContext.redirect(externalContext.getRequestContextPath() + "error/login_error.xhtml");

			}

			
		} catch (Exception e) {
			e.getMessage();

		}
	}

	public void logout() throws IOException {
		@SuppressWarnings("unused")
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		//externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
	@SuppressWarnings("unlikely-arg-type")
	public boolean hasRole(UserType type) {
        return listRole.contains(type);
    }

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String password = value.toString();

		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("passwordConfirmation");
		String passwordConfirmation = uiInputConfirmPassword.getSubmittedValue().toString();

		if (password == null || password.isEmpty() || passwordConfirmation == null || passwordConfirmation.isEmpty()) {
			return;
		}

		if (!password.equals(passwordConfirmation)) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(
					new FacesMessage("Fjalekalimi duhet te perputhet me konfirmimin e fjalekalimit!"));
		}

	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}
	
	
	
	
	
	
}
