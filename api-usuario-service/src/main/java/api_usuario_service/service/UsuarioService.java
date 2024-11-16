package api_usuario_service.service;

import api_usuario_service.entity.Usuario;
import api_usuario_service.exception.ResourceNotFoundException;

import java.util.List;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Usuario getUsuario(String usuarioId) throws ResourceNotFoundException;

}
