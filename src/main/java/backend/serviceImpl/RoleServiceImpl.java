package backend.serviceImpl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dao.RoleDao;
import backend.model.Role;
import backend.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roledao;
	final static org.apache.log4j.Logger logger = LogManager.getLogger(RoleServiceImpl.class);

	@Override
	public List<Role> listRoles() {
		try {
			return roledao.listAll();
		} catch (Exception e) {
			logger.debug("No results");
			logger.info(e.getMessage());
		}
		return null;
	}

	@Override
	public Role getRoleById(int id) {
		return roledao.getRoleById();
	}

	public RoleDao getRoledao() {
		return roledao;
	}

	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;
	}

	@Override
	public List<Role> getRolesA(int id) {
		return roledao.getRolesA(id);
	}

}
