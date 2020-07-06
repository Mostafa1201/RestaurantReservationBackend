package io.taher.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.taher.auth.WebSecurityConfig;
import io.taher.models.Reservation;
import io.taher.models.RestaurantTable;
import io.taher.models.User;
import io.taher.repositories.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
	public List<Reservation> getAllReservations(){
		List<Reservation> reservations = new ArrayList<>(); 
		reservationRepository.findAll().forEach(reservations::add);
		return reservations;
	}
	
	public Reservation getReservation(int id) {
		return reservationRepository.findById(id).orElse(null);
	}
	
	public Reservation createReservation(Map<String, Object> requestObject) {
		Reservation newReservation = new Reservation();
		User user = new User();
		RestaurantTable table = new RestaurantTable();
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String formattedDate = null;
		try {
			date = simpleDateFormat.parse(requestObject.get("date").toString());
			formattedDate = simpleDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		user.setId((int)requestObject.get("user"));
		newReservation.setUser(user);
		table.setId((int)requestObject.get("table"));
		newReservation.setTable(table);
		newReservation.setNoOfSeats((int) requestObject.get("noOfSeats"));
		newReservation.setDate(formattedDate);
		reservationRepository.save(newReservation);
		return newReservation;
	}

	public void editReservation(Reservation reservation, int id) {
		reservationRepository.save(reservation);
	}

	public void deleteReservation(Reservation reservation) {
		reservationRepository.delete(reservation);
	}
}
