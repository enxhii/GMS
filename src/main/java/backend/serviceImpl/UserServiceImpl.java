package backend.serviceImpl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.dao.UserDao;
import backend.model.Address;
import backend.model.Customer;
import backend.model.Role;
import backend.model.User;
import backend.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;
	final static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private List<User> users;

	@Override
	public boolean doesExists(String username) {
		if (userdao.doesExists(username)) {
			System.out.println("ServiceImpl works");
			System.out.println("username");

			return true;
		}

		return false;
	}

	@Override
	public List<User> listAll() {
		try {
			logger.info("Result Found //UserServiceImpl");
			users = userdao.listAll();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage() + "Error catched in ServiceImpl");
		}
		return null;

	}

	@Override
	public User getById(int id) {
		return null;
	}

	@Override
	public Integer getUserIdByUsername(String username) {
		return null;
	}

	@Override
	public User getUser(String username, String password) {
		try {
			logger.debug("Getting user data.....");
			return userdao.getUser(username, password);
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.debug("User not found");
		}
		return null;
	}

	@Override
	public void save(User entity, Address address, Role role) {
		try {
			userdao.addUser(entity, address,role);
			logger.info("User succesfully added");
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			logger.info("Something went wrong");
		}
	}

	@Override
	public List<Role> findByUser(int id) {
		logger.info("succedd");
		return userdao.findByUser(id);
	}

	@Override
	public void customerReg(User user, Address address) {
		try {
			logger.debug("Inserting user......");
			userdao.customerReg(user, address);
		} catch (Exception e) {
			// logger.debug(e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer id) {
		userdao.deleteUser(id);
	}

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public void updatePassword(User user, String password) {
		try {
			logger.debug("Updating password");
			userdao.updatePassword(user, password);
			logger.debug("Password updated ");

		} catch (Exception e) {
			logger.debug("An error happened " + e);
		}

	}

	@Override
	public void updateProfile(User user, Address address) {
		try {
userdao.updateProfile(user, address);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	@Override
	public void updateUsers(User user, Address address) {
try {
userdao.updateUser(user, address);	
} catch (Exception e) {
logger.debug(e);}	}

}
