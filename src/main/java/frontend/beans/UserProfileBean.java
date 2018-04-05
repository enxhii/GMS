package frontend.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import backend.model.User;

@SessionScoped
@ManagedBean
public class UserProfileBean {

	private User user;
	private boolean logged;

	public void removeUser() {
		user = null;

		logged = false;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

}
