package api_usuario_service.service.impl;

import api_usuario_service.entity.Calificacion;
import api_usuario_service.entity.Hotel;
import api_usuario_service.entity.Usuario;
import api_usuario_service.exception.ResourceNotFoundException;
import api_usuario_service.external.service.HotelService;
import api_usuario_service.repository.UsuarioRepository;
import api_usuario_service.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HotelService hotelService;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) throws ResourceNotFoundException {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID " + usuarioId));

        Calificacion[] calificacionesDelUsuario = restTemplate
                .getForObject("http://API-CALIFICACION-SERVICE/calificaciones/usuarios/"+usuario.getUsuarioId(), Calificacion[].class);

        List<Calificacion> calificaciones = Arrays.stream(calificacionesDelUsuario).collect(Collectors.toList());

        List<Calificacion> calificacionList = calificaciones.stream().map(calificacion -> {

            System.out.println(calificacion.getHotelId());

            //Se comenta para demostrar uso de Feign
            //ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://API-HOTEL-SERVICE/hoteles/"+calificacion.getHotelId(), Hotel.class);

            //Se usa Feign para demostrar uso del cliente REST
            Hotel entity = hotelService.obtenerHotelPorId(calificacion.getHotelId());

            //logger.info("{}", entity.getStatusCode());
            logger.info("{}", entity);

            //Hotel hotel = entity.getBody();

            calificacion.setHotel(entity);

            return calificacion;
        }).collect(Collectors.toList());

        logger.info("{}", calificacionList);

        usuario.setCalificaciones(calificacionList);

        return usuario;
    }
}
