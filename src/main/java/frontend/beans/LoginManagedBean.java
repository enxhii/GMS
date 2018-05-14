package frontend.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import backend.model.Role;
import backend.model.User;
import backend.service.RoleService;
import backend.service.UserService;

@ManagedBean(name = "login")
@RequestScoped
public class LoginManagedBean {
	private String username;
	private String password;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;

	private User user;
	private Role role;
	private List<User> userlist;
	private List<Role> listRole;
	final static Logger LOGGER = LogManager.getLogger(LoginManagedBean.class);
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

	public String submit() throws IOException {
		try {
			LOGGER.debug(userService.getUser(username, password));
			if (userService.doesExists(username)) {
				user = userService.getUser(username, password);
				userProfileBean.setUser(user);
				if (!user.getRoles().isEmpty()) {
					LOGGER.debug("User with username " + user.getUsername() + "logged in");
					return "success";
				}
			} else {
				LOGGER.debug("User doesn't exists");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "failure";
	}

	public String logout() throws IOException {
		user = userProfileBean.getUser();
		user = null;
		externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
		return "logout";
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

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
