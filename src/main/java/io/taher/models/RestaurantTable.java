package io.taher.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "restaurant_table")
public class RestaurantTable extends ModelBase {

	private String name;
	@Column(name = "no_of_seats")
	private int noOfSeats;
	@Column(name = "available_seats")
	private int availableSeats;
	private static final long serialVersionUID = 5926468583005150707L;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	public RestaurantTable() {}
		
	public RestaurantTable(int id, String name, int noOfSeats, int availableSeats, User user) {
		super(id);
		this.name = name;
		this.noOfSeats = noOfSeats;
		this.availableSeats = availableSeats;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}	
	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
