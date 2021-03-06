package backend.service;

import java.util.List;

import backend.model.Role;

public interface RoleService {
	public List<Role> listRoles();

	public Role getRoleById(int id);

	public List<Role> getRolesA(int id);
}
