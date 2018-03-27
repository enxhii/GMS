package backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dao.RoleDao;
import backend.model.UserRole;
import backend.pojo.Role;
import backend.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{
	
@Autowired
private  RoleDao roledao ;
@Override
	public List<Role> listRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole getUserRole(String username) {
		System.out.println("RoleServiceIMPLCLASS");
		return roledao.getUserRole(username);
	}

	public RoleDao getRoledao() {
		return roledao;
	}

	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;
	}

}
