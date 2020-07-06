package io.taher.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.taher.models.Reservation;
import io.taher.models.RestaurantTable;


public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	@Query(value =
			"SELECT * FROM reservation r "+
			"WHERE r.date >= :dateFrom "+
			"AND r.date <= :dateTo"
			,nativeQuery = true)
	public Page<Reservation> getReservationsBetweenDates(
			@Param("dateFrom") String dateFrom,
			@Param("dateTo") String dateTo,
			Pageable pageable);
	
}
