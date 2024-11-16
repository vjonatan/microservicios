package api_usuario_service.controller;

import api_usuario_service.entity.Usuario;
import api_usuario_service.exception.ResourceNotFoundException;
import api_usuario_service.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario (@RequestBody Usuario request) {
        Usuario usuario = usuarioService.saveUsuario(request);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    int cantidadReintentos = 1;

    @GetMapping("/{usuarioId}")
    //@CircuitBreaker(name = "circuitBreakerRatingHotelFallback", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "circuitBreakerRatingHotelFallback", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String usuarioId) throws ResourceNotFoundException {
        log.info("Listar un solo usuario");
        log.info("Cantidad de reintentos {}", cantidadReintentos);
        cantidadReintentos ++;

        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    public ResponseEntity<Usuario> ratingHotelFallback(String usuarioId, Exception e) {
        log.info("El respaldo se ejecuta porque el servicio está INACTIVO: {}", e.getMessage());

        Usuario usuario = Usuario.builder()
                .nombre("Jonatan")
                .email("jonatan@gmail.com")
                .informacion("Este usuario se crea por defecto cuando un servicio se cae.")
                .build();

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
