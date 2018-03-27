package backend.service;

import java.util.List;

import backend.model.UserRole;
import backend.pojo.Role;

public interface RoleService {
public List<Role> listRoles();
	public Role getRoleById(int id);
	public UserRole getUserRole(String username) ;

}
