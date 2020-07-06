package io.taher.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import io.taher.auth.WebSecurityConfig;
import io.taher.models.RestaurantTable;
import io.taher.models.User;
import io.taher.repositories.RestaurantTableRepository;
import io.taher.specifications.RestaurantSpecifications;


@Service
public class RestaurantTableService {
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	private static final Logger logger = LoggerFactory.getLogger(RestaurantTableService.class);
	public List<RestaurantTable> getAllRestaurantTables(){
		List<RestaurantTable> tables = new ArrayList<>(); 
		restaurantTableRepository.findAll().forEach(tables::add);
		return tables;
	}
	
	public RestaurantTable createRestaurantTable(Map<String, Object> requestObject) {
		User user = new User();
		user.setId((int)requestObject.get("user"));
		RestaurantTable table = new RestaurantTable();
		table.setUser(user);
		table.setName(requestObject.get("name").toString());
		table.setNoOfSeats((int)requestObject.get("noOfSeats"));
		table.setAvailableSeats((int)requestObject.get("noOfSeats"));
		return restaurantTableRepository.save(table);
	}

	/**
	 * Get available restaurant tables for customers 
	 * This function has 2 filters 
	 * 1- available seats filter
	 * 2- date filter which gets tables that is available by time
	 * @param params
	 * @return
	 */
	public Map<String, Object> getAvailableRestaurantTables(Map<String, Object> params){
		int page = params.get("page") != null ? (int) params.get("page") : 1;
		int size = params.get("size") != null ? (int) params.get("size") : 8;
		int availableSeats = params.get("noOfSeats") != null ? (int) params.get("noOfSeats") : 0;
		String dateFrom = params.get("date").toString() + " 00:00:00";
		String dateTo = params.get("date").toString() + " 23:59:59";
		String userType = params.get("userType").toString().trim();
		Pageable pageable = (Pageable) PageRequest.of(page, size);
		logger.warn("userType: "+ userType);
		Page<RestaurantTable> result;
		if(userType.equals("admin")) { // if user type admin show all defined tables
			result = restaurantTableRepository.findAll(pageable);
		}
		else {
			result = restaurantTableRepository.findAvailableTablesByDate(availableSeats,dateFrom,dateTo,pageable); 
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("count", result.getNumberOfElements());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());
		return response;
	}
}
