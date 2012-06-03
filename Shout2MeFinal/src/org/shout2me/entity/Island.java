package org.shout2me.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

@Entity
@NamedQuery(name = "Island.findAll", query = "Select i from Island i")
public class Island implements Serializable {

	private static final long serialVersionUID = -5469898599211774319L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

//	island with shape
//	@Type(type = "org.hibernatespatial.GeometryUserType")
//	@Column(columnDefinition = "GEOMETRY")
//	private Polygon shape;
	
	@Type(type = "org.hibernatespatial.GeometryUserType")
	@Column(columnDefinition = "GEOMETRY")
	private Point location;

	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "destination", targetEntity = Message.class, fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
	private Collection<Message> messages;
	
	@Column(nullable = false)
	private Double rank;
	
	@OneToOne
	private User owner;

	public Island() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
