package org.shout2me.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m") })
public class Message implements Serializable {

	private static final long serialVersionUID = 3228741148418384569L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String topic;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "user_id", nullable = false)
	private User author;

	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "island_id", nullable = false)
	private Island destination;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Island getDestination() {
		return destination;
	}

	public void setDestination(Island destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
