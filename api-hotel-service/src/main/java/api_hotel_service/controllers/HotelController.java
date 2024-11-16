package api_hotel_service.controllers;

import api_hotel_service.entity.Hotel;
import api_hotel_service.excepcions.ResourceNotFoundException;
import api_hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> create (@RequestBody Hotel request) {
        Hotel saved = hotelService.create(request);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> get(@PathVariable String hotelId) throws ResourceNotFoundException {
        Hotel hotels = hotelService.get(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotels = hotelService.getALl();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }


}
