package diploma.sportlife.exception;

public class MismatchedException extends RuntimeException {
    public MismatchedException() {
        super("Mismatched request data");
    }
}
