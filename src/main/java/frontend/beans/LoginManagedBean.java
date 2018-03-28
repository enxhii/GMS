package frontend.beans;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import backend.SessionUtils.SessionUtils;
import backend.model.Role;
import backend.model.User;
import backend.service.UserService;
@ManagedBean(name = "login")
@RequestScoped
public class LoginManagedBean {
	private static final String LOGOUT = "logout";
	private String username;
	private String password;
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	private User user;
	private Role role;
	private List<User> userlist;
	final static Logger logger = LogManager.getLogger(LoginManagedBean.class);
	public void submit() throws IOException {
		try {
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if (userService.doesExists(username)) {
			
			logger.debug("Checking if user exists ");
			user = userService.getUser(username, password);
			HttpSession session = SessionUtils.getSession();
				session.setAttribute("id", user.getId());
				session.setAttribute("username",user.getUsername());
		        logger.debug("User Found");
		       // if(userService.findByUser(user.getId()) == (role.getId()){
				externalContext.redirect(externalContext.getRequestContextPath() + "/admin/admin.xhtml");
		        logger.debug("User with username " + username +"logged in");

				}
		else {
	        logger.error("User doesn't exists");
	        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");

		}

		} catch (Exception e) {
			e.getMessage();
		}
	}
	public void logout() throws IOException {
		@SuppressWarnings("unused")
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");

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

	public static String getLogout() {
		return LOGOUT;
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

}
