package frontend.beans;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import backend.service.RoleService;
import backend.service.*;
import backend.model.*;

@ManagedBean(name = "userbean")
@ViewScoped
public class UserBean {
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;
	final static Logger logger = LogManager.getLogger(UserBean.class);
	private String password;
	private String confirmPass;
	public boolean logged;
	private Address address;
	private List<Role> list;
	private List<User> users;
	private Customer customer;
	private User user;
	private Role role;
	private User userDelete;
	private User update;

	@PostConstruct
	public void init() {
		user = new User();
		address = new Address();
		list = roleService.listRoles();
		customer = new Customer();
		role = new Role();
		users = userService.listAll();
		update = new User();

	}

	public void addUser() {
		userService.save(user, address, role);
	}

	public void addCustomer() {
		userService.customerReg(user, address);
	}

	public void deleteUser() {
		users.remove(userDelete);
		userService.delete(userDelete.getId());
	}

	public List<User> listAll() {
		try {
			users = userService.listAll();
			return users;
		} catch (Exception e) {
			logger.debug(e);
		}
		return users;

	}

	public List<Role> listRoles() {
		list = roleService.listRoles();
		return list;
	}

	public void updatePassword() {
		userService.updatePassword(userProfileBean.getUser(), password);
	}

	public void updateProfile() {
		userService.updateProfile(userProfileBean.getUser(), userProfileBean.getUser().getAddress());
	}

	public void updateUsers() {
		userService.updateUsers(update, address);
		userService.listAll();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getList() {
		return list;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public User getUserDelete() {
		return userDelete;
	}

	public void setUserDelete(User userDelete) {
		this.userDelete = userDelete;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public UserProfileBean getUserProfileBean() {
		return userProfileBean;
	}

	public void setUserProfileBean(UserProfileBean userProfileBean) {
		this.userProfileBean = userProfileBean;
	}

	public User getUpdate() {
		return update;
	}

	public void setUpdate(User update) {
		this.update = update;
	}

}
