package backend.service;

import backend.pojo.User;

public interface RegisterService {
public boolean doesExist(String email);
	public void register(User user);
}
