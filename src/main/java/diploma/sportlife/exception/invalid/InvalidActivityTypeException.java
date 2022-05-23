package diploma.sportlife.exception.invalid;

import org.springframework.http.converter.HttpMessageConversionException;

public class InvalidActivityTypeException extends HttpMessageConversionException {
    public InvalidActivityTypeException(String exceptionDescription) {
        super(String.format("Invalid Activity Type. Type: {%s} is rejected", exceptionDescription));
    }
}
