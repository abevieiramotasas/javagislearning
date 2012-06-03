package org.shout2me.entity.to;

import java.util.Date;

import org.shout2me.entity.Message;

public class MessageTO {
	private Long id;
	private String text;
	private String topic;
	private Date date;
	private UserTO author;
	private IslandTO destination;
	
	public MessageTO(Message message) {
		this.id = message.getId();
		this.text = message.getText();
		this.topic = message.getTopic();
		this.date = message.getDate();
		this.author = new UserTO(message.getAuthor());
		this.destination = new IslandTO(message.getDestination());
	}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public IslandTO getDestination() {
		return destination;
	}
	public void setDestination(IslandTO destination) {
		this.destination = destination;
	}
	public UserTO getAuthor() {
		return author;
	}
	public void setAuthor(UserTO author) {
		this.author = author;
	}
	

}
