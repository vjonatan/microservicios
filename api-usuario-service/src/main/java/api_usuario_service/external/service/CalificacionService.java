package api_usuario_service.external.service;

import api_usuario_service.entity.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("API-CALIFICACION-SERVICE")
public interface CalificacionService {

    @GetMapping("/calificaciones/usuarios/{usuarioId}")
    public ResponseEntity<List<Calificacion>> obtenerCalificaciones(@PathVariable String usuarioId);

    @PostMapping
    public ResponseEntity<Calificacion> guardarCalificacion(@RequestBody Calificacion request);

    @DeleteMapping("/calificaciones/calificacion/{calificacionId}")
    public void eliminarCalificacion(@PathVariable String calificacionId);
}
