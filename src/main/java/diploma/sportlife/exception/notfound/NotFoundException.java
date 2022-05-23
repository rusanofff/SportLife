package diploma.sportlife.exception.notfound;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Entity not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
