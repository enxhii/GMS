package frontend.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import backend.model.User;
import backend.service.RoleService;

@SessionScoped
@ManagedBean
public class UserProfileBean {
	private RoleEnum roleEnum;
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
	private User user;
	private boolean logged;
	final static Logger logger = LogManager.getLogger(UserProfileBean.class);

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

	public boolean hasRoleAdmin(RoleEnum role) {
		try {
			logger.debug(user.getId());
			logger.debug(roleService.getRolesA(user.getId()));
			return roleService.getRolesA(user.getId()).contains(role.ADMIN.toString());
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;

	}

	public boolean hasRoleTrainer(RoleEnum role) {
		logger.debug(user.getId());
		logger.debug(roleService.getRolesA(user.getId()));
		return roleService.getRolesA(user.getId()).contains(role.TRAINER.toString());
	}

	public boolean hasRoleMember(RoleEnum role) {
		logger.debug(user.getId());
		logger.debug(roleService.getRolesA(user.getId()));
		return roleService.getRolesA(user.getId()).contains(role.MEMBER.toString());
	}

	public RoleEnum getRoleEnum() {
		return roleEnum;
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
