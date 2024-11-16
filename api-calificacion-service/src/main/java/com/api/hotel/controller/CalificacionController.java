package com.api.hotel.controller;

import com.api.hotel.entities.Calificacion;
import com.api.hotel.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    CalificacionService calificacionService;

    @PostMapping
    ResponseEntity<Calificacion> crear(@RequestBody Calificacion request){
        Calificacion saved = calificacionService.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Calificacion>> getAll(){
        List<Calificacion> calificaciones = calificacionService.getCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Calificacion>> getCalificacionesByUsuarioId(@PathVariable String usuarioId){
        List<Calificacion> calificacions = calificacionService.getCalificacionesByUsuarioId(usuarioId);
        return new ResponseEntity<>(calificacions, HttpStatus.OK);
    }

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Calificacion>> getCalificacionesByHotelId(@PathVariable String hotelId){
        List<Calificacion> calificacions = calificacionService.getCalificacionesByHotelId(hotelId);
        return new ResponseEntity<>(calificacions, HttpStatus.OK);
    }

}
