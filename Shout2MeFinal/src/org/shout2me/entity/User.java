package org.shout2me.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "Select u from User u"),
	@NamedQuery(name = "User.existsMail", query = "Select Count(u) From User u Where u.mail = :user_mail"),
	@NamedQuery(name = "User.findByMail", query = "Select u from User u Where u.mail = :user_mail")})
@Table(name = "UserSistema")
public class User implements Serializable {

	private static final long serialVersionUID = 8828030645201684177L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String mail;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String salt;

	@OneToMany(mappedBy = "author", targetEntity = Message.class, fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
	private Collection<Message> messages;

	public User() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
