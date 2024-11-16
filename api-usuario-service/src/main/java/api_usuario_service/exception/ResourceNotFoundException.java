package api_usuario_service.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException() {
        super("Recurso no encontrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
