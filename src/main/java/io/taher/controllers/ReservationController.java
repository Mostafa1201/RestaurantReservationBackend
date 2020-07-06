package io.taher.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.taher.models.Reservation;
import io.taher.services.ReservationService;


@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "/reservations",method = RequestMethod.GET)
	public List<Reservation> test() {
		return reservationService.getAllReservations();
	}
	
	@RequestMapping(value = "/reservations/{id}",method = RequestMethod.GET)
	public Reservation getReservation(@PathVariable int id) {
		return reservationService.getReservation(id);
	}
	
	@RequestMapping(value = "/reservations/add",method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Reservation createReservation(@RequestBody Map<String, Object> requestObject) {
		return reservationService.createReservation(requestObject);
	}
	
	@RequestMapping(value = "/reservations/{id}",method = RequestMethod.PUT)
	public void editReservation(@RequestBody Reservation reservation,@PathVariable int id) {
		reservationService.editReservation(reservation, id);
	}
	
	@RequestMapping(value = "/reservations/{id}",method = RequestMethod.DELETE)
	public void deleteReservation(@RequestBody Reservation reservation) {
		reservationService.deleteReservation(reservation);
	}
	
	
	
	
}
