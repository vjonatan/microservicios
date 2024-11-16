package api_hotel_service.excepcions;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
