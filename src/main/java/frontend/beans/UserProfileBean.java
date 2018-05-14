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
	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;
	private User user;
	final static Logger LOGGER = LogManager.getLogger(UserProfileBean.class);

	public void removeUser() {
		user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean hasRoleAdmin(RoleEnum role) {
		try {
			LOGGER.debug(user.getId());
			LOGGER.debug(roleService.getRolesA(user.getId()));
			return roleService.getRolesA(user.getId()).contains(RoleEnum.ADMIN.toString());
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return false;

	}

	public boolean hasRoleTrainer(RoleEnum role) {
		LOGGER.debug(user.getId());
		LOGGER.debug(roleService.getRolesA(user.getId()));
		return roleService.getRolesA(user.getId()).contains(RoleEnum.TRAINER.toString());
	}

	public boolean hasRoleMember(RoleEnum role) {
		LOGGER.debug(user.getId());
		LOGGER.debug(roleService.getRolesA(user.getId()));
		return roleService.getRolesA(user.getId()).contains(RoleEnum.MEMBER.toString());
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
