package org.shout2me.entity.to;

import org.shout2me.entity.Island;


public class IslandTO {

	private Long id;

	private String name;
	
	private Long longitude;
	
	private Long latitude;
	
	private Double rank;

	private String description;
	
	public IslandTO(Island island) {
		this.id = island.getId();
		this.name = island.getName();
		this.longitude = (long) island.getLocation().getX();
		this.latitude = (long) island.getLocation().getY();
		this.rank = island.getRank();
		this.description = island.getDescription();
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

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
}
