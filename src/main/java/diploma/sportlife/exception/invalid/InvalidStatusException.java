package diploma.sportlife.exception.invalid;

import org.springframework.http.converter.HttpMessageConversionException;

public class InvalidStatusException extends HttpMessageConversionException {

    public InvalidStatusException(String exceptionDescription) {
        super(String.format("Invalid status. Status: {%s} is rejected", exceptionDescription));
    }
}
