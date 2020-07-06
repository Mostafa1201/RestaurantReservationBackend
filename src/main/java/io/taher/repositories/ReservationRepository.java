package io.taher.repositories;

import org.springframework.data.repository.CrudRepository;

import io.taher.models.Reservation;


public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}
