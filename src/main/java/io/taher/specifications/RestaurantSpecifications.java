package io.taher.specifications;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import io.taher.models.RestaurantTable;

public class RestaurantSpecifications {
    public static Specification<RestaurantTable> withReservationDateEquals() {
//        if (date == null) {
//            return null;
//        } else {
//        	DateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
//    		Date dateFrom = null;
//    		Date dateTo = null;
//    		try {
//    			dateFrom = simpleDateFormat.parse(date.toString()+ "00:00:00");
//    			dateTo = simpleDateFormat.parse(date.toString()+ "23:59:59");
//    		} catch (ParseException e) {
//    			e.printStackTrace();
//    		}
//            return (root, query, cb) -> cb.equal(root.join("reservation"), root.get("id"));
//        }
        return (root, query, cb) -> cb.equal(root.join("reservation"), root.get("id"));
    }

    // TODO: Implement withModel, withVehicleType, withBrand, ...
}