package backend.service;

import backend.model.*;

public interface LoginService {
	public User getUser(String username, String password);

	public boolean doesExists(String username);

	public void logOut();
}
