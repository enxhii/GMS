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

import backend.encryption.PasswordEncryptor;
import backend.model.Address;
import backend.model.Customer;
import backend.model.Role;
import backend.model.User;
import backend.service.RoleService;
import backend.service.UserService;

@ManagedBean(name = "userbean")
@ViewScoped
public class UserBean {
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;

	@ManagedProperty(value = "#{userProfileBean}")
	private UserProfileBean userProfileBean;

	private String databasePassword;
	final static Logger LOGGER = LogManager.getLogger(UserBean.class);
	private String password;
	private String confirmPass;
	public boolean logged;
	private Address address;
	private List<Role> list;
	private List<Role> selectedRole;
	private List<User> users;
	private Customer customer;
	private User user;
	private Role role;
	private User userDelete;
	private User update;
	private User updatepass;

	private List<User> disabledUsers;
	private List<User> disabledCustomers;
	private User checked;
	private static final String PF_ADDUSER_DIALOG_HIDE = "PF('AddUserDialog').hide()";
	private static final String PF_EditPasswordDialog_HIDE = "PF('EditPasswordDialog').hide()";
	private static final String PF_EditUserDialog_HIDE = "PF('EditUserDialog').hide()";

	@PostConstruct
	public void init() {
		user = new User();
		address = new Address();
		list = roleService.listRoles();
		customer = new Customer();
		role = new Role();
		users = userService.listAll();
		update = new User();
		disabledCustomers = userService.getDisabledCustomer();
		disabledUsers = userService.getDisabledUser();
		checked = new User();
		selectedRole = roleService.listRoles();
		databasePassword = userProfileBean.getUser().getPassword();
		updatepass = new User();
	}

	public void addUser() {
		if (!userService.doesExists(user.getUsername())) {
			LOGGER.debug(selectedRole);
			userService.save(user, address, selectedRole);
			addMessage("User" + "" + user.getUsername() + "" + "succesfully registered");
			executeScript(PF_ADDUSER_DIALOG_HIDE);
		} else {
			addMessage("Username " + " " + user.getUsername() + "   " + "is taken.Please try another one !");
		}
	}

	public void deleteUser() {
		if (userProfileBean.getUser().getId() == userDelete.getId()) {
			addMessage("You can't delete your self!");
		} else {
			users.remove(userDelete);
			userService.delete(userDelete.getId());
			users = userService.listAll();
			addMessage("User " + "  " + userDelete.getUsername() + " " + "was  deleted succesfully");
		}
	}

	public List<User> listAll() {
		try {
			users = userService.listAll();
			return users;
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return users;

	}

	public List<Role> listRoles() {
		list = roleService.listRoles();
		return list;
	}

	public void updatePassword() throws Exception {
		LOGGER.debug("Database PAssword" + databasePassword);
		LOGGER.debug("Comparing" + PasswordEncryptor.encrypt(userProfileBean.getUser().getPassword()));

		if (!PasswordEncryptor.encrypt(userProfileBean.getUser().getPassword()).equals(databasePassword)) {
			LOGGER.debug((!userProfileBean.getUser().getPassword().equals(databasePassword)));
			LOGGER.debug(userProfileBean.getUser().getPassword());
			addMessage("Wrong Password  ");
		} else if (PasswordEncryptor.encrypt(userProfileBean.getUser().getPassword()).equals(databasePassword)) {
			userService.updatePassword(userProfileBean.getUser(), password);
			addMessage("Password Succesfully updated ");
		}

	}

	public void updateUsersPassword() {
		if (password.equals(confirmPass)) {
			LOGGER.debug("Passwordi" + password);
			LOGGER.debug("Konfirmim pass" + confirmPass);
			userService.updatePassword(updatepass, password);
			addMessage(updatepass.getUsername() + "  " + "Password Succesfully updated");
			executeScript(PF_EditPasswordDialog_HIDE);
		} else {
			addMessage("Passwords didnt matched.Try again!");

		}
	}

	public void updateProfile() {
		userService.updateProfile(userProfileBean.getUser(), userProfileBean.getUser().getAddress());
		addMessage("Your Profile Succesfully updated");

	}

	public void updateUsers() {
		userService.updateUsers(update, update.getAddress(), update.getRoles());
		userService.listAll();
		addMessage("User " + "  " + update.getUsername() + " " + " " + "Succesfully updated");
		executeScript(PF_EditUserDialog_HIDE);
	}

	public Role getRole(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id not null");
		}
		Optional<Role> optional = list.stream().filter(role -> id.equals(role.getId())).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void enableUser() {
		disabledUsers.remove(checked);
		userService.enableUsers(checked.getId());
		disabledUsers = userService.getDisabledUser();
		addMessage("User " + checked.getUsername() + "" + "is now active ");

	}

	public void giveAccess() {
		disabledCustomers.remove(checked);
		userService.giveAccess(checked.getId());
		LOGGER.debug(checked.getId());
		disabledCustomers = userService.getDisabledCustomer();
		addMessage("Access given Succesully to:" + "" + checked.getUsername());

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

	public List<User> getDisabledCustomer() {
		return disabledCustomers;

	}

	private void executeScript(String script) {
		requestContext().execute(script);
	}

	public List<User> getDisUsers() {
		return disabledUsers;

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

	public List<Role> getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(List<Role> selectedRole) {
		this.selectedRole = selectedRole;
	}

	public List<User> getDisabledCustomers() {
		return disabledCustomers;
	}

	public void setDisabledCustomers(List<User> disabledCustomers) {
		this.disabledCustomers = disabledCustomers;
	}

	public List<User> getDisabledUsers() {
		return disabledUsers;
	}

	public void setDisabledUsers(List<User> disabledUsers) {
		this.disabledUsers = disabledUsers;
	}

	public User getChecked() {
		return checked;
	}

	public void setChecked(User checked) {
		this.checked = checked;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public User getUpdatepass() {
		return updatepass;
	}

	public void setUpdatepass(User updatepass) {
		this.updatepass = updatepass;
	}

}
