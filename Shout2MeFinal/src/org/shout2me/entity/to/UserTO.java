package org.shout2me.entity.to;

import org.shout2me.entity.User;

public class UserTO {
	
	private Long id;
	
	private String mail;
	
	private String description;
	
	
	public UserTO(User user) {
		this.setId(user.getId());
		this.setMail(user.getMail());
		this.setDescription(user.getDescription());
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
