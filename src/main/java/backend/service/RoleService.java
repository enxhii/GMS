package backend.service;

import java.util.List;

import backend.model.*;

public interface RoleService {
public List<Role> listRoles();
	public Role getRoleById(int id);

}
