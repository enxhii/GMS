package frontend.beans;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import backend.SessionUtils.SessionUtils;
import backend.model.Role;
import backend.model.User;
import backend.model.UserRole;
import backend.service.RoleService;
import backend.service.UserService;
@ManagedBean(name = "login")
@RequestScoped
public class LoginManagedBean {
	private static final String LOGOUT = "logout";
	private String username;
	private String password;
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
	private User user;
	private UserRole userRole;
	private Role role;

	public void submit() throws IOException {
		try {
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			if (userService.doesExists(username) && username != null) {
				user = userService.getUser(username, password);
				System.out.print(user.getUsername());
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("id", user.getId());
				session.setAttribute("username", username);
				externalContext.redirect(externalContext.getRequestContextPath() + "/adminPages/admin.xhtml");

			}	
			externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");

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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
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

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
