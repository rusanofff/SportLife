package diploma.sportlife.exception.invalid;

import org.springframework.http.converter.HttpMessageConversionException;

public class InvalidSportEventTypeException extends HttpMessageConversionException {

    public InvalidSportEventTypeException(String exceptionDescription) {
        super(String.format("Invalid Sport SportEvent Type. Type: {%s} is rejected", exceptionDescription));
    }
}
