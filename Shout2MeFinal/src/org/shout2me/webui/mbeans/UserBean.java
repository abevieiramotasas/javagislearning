package org.shout2me.webui.mbeans;

import javax.faces.bean.ManagedBean;

import org.shout2me.entity.User;

@ManagedBean(name="user")
public class UserBean {
	private User u = new User();

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
}
