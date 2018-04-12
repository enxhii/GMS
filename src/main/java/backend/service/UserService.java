package backend.service;

import java.util.List;
import backend.model.*;

public interface UserService {
	public boolean doesExists(String username);

	public User getById(int id);

	public User getUser(String username, String password);

	Integer getUserIdByUsername(String username);

	public void save(User entity, Address address, List<Role> role);

	public void delete(Integer id);

	public List<User> getUserRoles();

	public void enableUsers(Integer id);

	public void giveAccess(Integer id);
	public List<User> getDisabledCustomer() ;
	public List<User> getDisabledUser() ;

	public void customerReg(User user, Address address);

	public List<User> listAll();

	public void updatePassword(User user, String password);

	public void updateProfile(User user, Address address);

	public void updateUsers(User user, Address address);
}
