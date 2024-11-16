package com.api.hotel.repository;

import com.api.hotel.entities.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CalificacionRepository extends MongoRepository<Calificacion, Long> {

    List<Calificacion> findByUsuarioId(String usuarioId);

    List<Calificacion> findByHotelId(String hotelId);
}
