package diploma.sportlife.exception.invalid;

import org.springframework.http.converter.HttpMessageConversionException;

public class InvalidSportEventTypeOpportunitiesException extends HttpMessageConversionException {
    public InvalidSportEventTypeOpportunitiesException(String exceptionDescription) {
        super(String.format("Invalid Sport Event Type Opportunities. Type: {%s} is rejected", exceptionDescription));
    }
}
