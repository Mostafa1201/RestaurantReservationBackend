package io.taher.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Map<String, Object> getAllReservations(Map<String, Object> params){
		int page = params.get("page") != null ? (int) params.get("page") : 1;
		int size = params.get("size") != null ? (int) params.get("size") : 8;
		String dateFrom = params.get("date").toString() + " 00:00:00";
		String dateTo = params.get("date").toString() + " 23:59:59";
		Pageable pageable = (Pageable) PageRequest.of(page, size);
		Page<Reservation> result = reservationRepository.getReservationsBetweenDates(dateFrom,dateTo,pageable);
		Map<String, Object> response = new HashMap<>();
		response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("count", result.getNumberOfElements());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());
		return response;
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

	public void deleteReservation(Reservation reservation) {
		reservationRepository.delete(reservation);
	}
}
