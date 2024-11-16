package com.api.hotel.service;

import com.api.hotel.entities.Calificacion;

import java.util.List;

public interface CalificacionService {


    Calificacion create(Calificacion calificacion);

    List<Calificacion> getCalificaciones();

    List<Calificacion> getCalificacionesByUsuarioId(String usuarioId);

    List<Calificacion> getCalificacionesByHotelId(String hotelId);

}
