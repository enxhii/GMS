package backend.service;

import java.util.List;

import backend.model.Address;
import backend.model.Role;
import backend.model.User;

public interface UserService {
	public User getUser(String username, String password);

	public boolean doesExists(String username);

	public void save(User entity, Address address, List<Role> role);

	public void delete(Integer id);

	public void enableUsers(Integer id);

	public void giveAccess(Integer id);

	public List<User> getDisabledCustomer();

	public List<User> getDisabledUser();

	public List<User> listAll();

	public void updatePassword(User user, String password);

	public void updateProfile(User user, Address address);

	public void updateUsers(User user, Address address, List<Role> roles);
}
