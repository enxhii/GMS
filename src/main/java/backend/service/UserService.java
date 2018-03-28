package backend.service;
import java.util.List;
import backend.model.*;

public interface UserService {
public boolean doesExists(String username);
public List<User> listAll();
public User getById(int id);
public User getUser(String username, String password);
Integer getUserIdByUsername(String username);
public void save(User entity,Address address);
public void update(User entity);
public void delete(User entity);
public void deleteById(int entityId);
public void logout();
public List<Role> findByUser(int id) ;
}
