package backend.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.dao.UserDao;
import backend.model.Address;
import backend.model.Role;
import backend.model.User;
import backend.service.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;

	@Override
	public boolean doesExists(String username) {
		if(userdao.doesExists(username)) {
			System.out.println("ServiceImpl works");
			System.out.println("username");

		return true;
	}
		System.out.println("ServiceImpl does not works");

	return  false ;
}
	@Override
	public List<User> listAll() {
		return null;
	}

	@Override
	public User getById(int id) {
		return null;
	}
	@Override
	public void update(User entity) {

	}

	@Override
	public void delete(User entity) {

	}

	@Override
	public void deleteById(int entityId) {

	}

	@Override
	public Integer getUserIdByUsername(String username) {
		return null;
	}

	@Override
	public User getUser(String username, String password) {
				return userdao.getUser(username,password);

	}
	@Override
	public void logout() {
		
	}
	@Override
	public void save(User entity, Address address) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Role> findByUser(int id) {
return userdao.findByUser(id);
	}

}