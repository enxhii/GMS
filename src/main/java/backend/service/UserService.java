package backend.service;
import java.util.List;
import backend.model.*;
public interface UserService extends GenericService<User>{
public boolean doesExists(String username);
public User getById(int id);
public User getUser(String username, String password);
Integer getUserIdByUsername(String username);
public void save(User entity,Address address,Customer customer ,Role role);
public void update(User entity);
public void delete(Integer id);
public List<Role> findByUser(int id) ;
public void customerReg(User user, Address address) ;
public List<User> listAll();

}
