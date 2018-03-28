package backend.service;

import backend.model.*;

public interface RegisterService {
public boolean doesExist(String email);
	public void register(User user);
}
