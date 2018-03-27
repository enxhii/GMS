package frontend.beans;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import backend.model.Address;
import backend.model.Role;
import backend.model.User;
import backend.model.UserRole;
import backend.service.UserService;
@ManagedBean(name="userbean")
@SessionScoped
public class UserBean {
private User user;
private Role role;
private UserRole userRole;
@ManagedProperty(value = "#{userServiceImpl}")
private UserService userService;
public boolean logged;
private Address address;
@PostConstruct
public void init() {
	user=new User();
	address= new Address();
	userRole= new UserRole();
}
public void addUser()
{
userService.save(user, address,userRole);
	}

	public void removeUser() {
	user = null;
	logged = false;
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
public UserRole getUserRole() {
	return userRole;
}
public void setUserRole(UserRole userRole) {
	this.userRole = userRole;
}

}
