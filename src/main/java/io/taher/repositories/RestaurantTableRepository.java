package io.taher.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.taher.models.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long>, JpaSpecificationExecutor<RestaurantTable> 
{

	@Query(value =
			"SELECT * FROM restaurant_table rt "+
			"LEFT JOIN reservation r ON r.table_id = rt.id " +
			"WHERE ((r.deleted != 0 AND (r.date < :dateTo OR r.date > :dateFrom)) " +
			"OR r.id IS NULL) " +
			"AND rt.available_seats >= :availableSeats " +
			"GROUP BY rt.id"
			,nativeQuery = true)
	public Page<RestaurantTable> findAvailableTablesByDate(
			@Param("availableSeats") int availableSeats,
			@Param("dateFrom") String dateFrom,
			@Param("dateTo") String dateTo,
			Pageable pageable);
}
