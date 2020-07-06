package io.taher.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "reservation")
public class Reservation extends ModelBase {
	
	@Column(name = "no_of_seats")
	private int noOfSeats;

	private String date;
	
	private static final long serialVersionUID = -3010927185870063816L;

	@ManyToOne
	@JoinColumn(name = "table_id")
	private RestaurantTable table;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Reservation() {}
	
	public Reservation(int id,RestaurantTable table, User user, int noOfSeats, String date) {
		super(id);
		this.table = table;
		this.user = user;
		this.noOfSeats = noOfSeats;
		this.date = date;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
