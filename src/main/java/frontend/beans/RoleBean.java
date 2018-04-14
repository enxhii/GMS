package frontend.beans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import backend.model.Role;
import backend.model.User;
import backend.service.RoleService;

@ManagedBean
@ViewScoped
public class RoleBean {
	private Role role;
	private List<Role> roles;
	private User u;
	@ManagedProperty(value="#{roleServiceImpl}")
	private RoleService roleService;
@PostConstruct
public void init() {
	role=new Role();
	u= new User();
	
}

public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}
public User getU() {
	return u;
}
public void setU(User u) {
	this.u = u;
}
public RoleService getRoleService() {
	return roleService;
}
public void setRoleService(RoleService roleService) {
	this.roleService = roleService;
}
}
