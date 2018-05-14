package backend.service;

import backend.model.User;

public interface RegisterService {
	public boolean doesExist(String email);

	public void register(User user);
}
