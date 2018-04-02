package frontend.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import backend.model.User;
@SessionScoped
@ManagedBean
public class UserProfileBean {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
