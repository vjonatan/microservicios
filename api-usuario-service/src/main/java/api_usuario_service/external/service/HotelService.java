package api_usuario_service.external.service;

import api_usuario_service.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("API-HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hoteles/{hotelId}")
    Hotel obtenerHotelPorId(@PathVariable String hotelId);
}
