package api_hotel_service.service;

import api_hotel_service.entity.Hotel;
import api_hotel_service.excepcions.ResourceNotFoundException;

import java.util.List;

public interface HotelService {
    Hotel create (Hotel hotel);
    List<Hotel> getALl();
    Hotel get(String hotelId) throws ResourceNotFoundException;
}
