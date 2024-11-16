package api_hotel_service.service.impl;

import api_hotel_service.entity.Hotel;
import api_hotel_service.excepcions.ResourceNotFoundException;
import api_hotel_service.repository.HotelRepository;
import api_hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getALl() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String hotelId) throws ResourceNotFoundException {
        return hotelRepository
                .findById(hotelId)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No se encuentra el Hotel con el ID " + hotelId)
                );
    }
}
