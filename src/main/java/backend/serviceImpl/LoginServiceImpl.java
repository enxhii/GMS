package backend.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.dao.LoginDao;
import backend.model.User;
import backend.service.LoginService;
@Transactional
@Service
public class LoginServiceImpl implements LoginService {
@Autowired
private LoginDao loginDao;
	@Override
	public User getUser(String username, String password) {
		return loginDao.getUser(username, password);
	}

	@Override
	public boolean doesExists(String username) {
		if (loginDao.doesExists(username)){
			return true;
		} 
		return false;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub

	}

}
