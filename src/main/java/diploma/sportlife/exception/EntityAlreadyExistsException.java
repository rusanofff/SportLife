package diploma.sportlife.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException() {
        super("Entity already exists");
    }
}

