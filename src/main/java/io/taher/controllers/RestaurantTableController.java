package io.taher.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.taher.auth.WebSecurityConfig;
import io.taher.models.RestaurantTable;
import io.taher.services.RestaurantTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RestaurantTableController {
	@Autowired
	private RestaurantTableService RestaurantTableService;
	private static final Logger logger = LoggerFactory.getLogger(RestaurantTableController.class);
	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public List<RestaurantTable> test() {
		return RestaurantTableService.getAllRestaurantTables();
	}

	@RequestMapping(value = "/tables/add", method = RequestMethod.POST)
	public RestaurantTable createRestaurantTable(@RequestBody Map<String, Object> requestObject) {
		return RestaurantTableService.createRestaurantTable(requestObject);
	}

	@RequestMapping(value = "/tables/{id}", method = RequestMethod.PUT)
	public void editRestaurantTable(@RequestBody RestaurantTable table, @PathVariable int id) {
		RestaurantTableService.editRestaurantTable(table, id);
	}

	@RequestMapping(value = "/tables/{id}", method = RequestMethod.DELETE)
	public void deleteRestaurantTable(@RequestBody RestaurantTable table) {
		RestaurantTableService.deleteRestaurantTable(table);
	}
	
	@RequestMapping(value = "/tables/available", method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAvailableRestaurantTables(
			@RequestBody Map<String, Object> requestObject
	) {
		Map<String, Object> params = new HashMap<>();
		params.put("page", requestObject.get("page"));
		params.put("size", requestObject.get("size"));
		if(requestObject.get("noOfSeats") != null) {
			params.put("noOfSeats", requestObject.get("noOfSeats"));
		}
		if(requestObject.get("date") != null) {
			params.put("date", requestObject.get("date"));
		}
		params.put("userType", requestObject.get("userType"));
		return RestaurantTableService.getAvailableRestaurantTables(params);
	}
	
}
