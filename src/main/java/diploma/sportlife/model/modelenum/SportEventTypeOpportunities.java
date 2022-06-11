package diploma.sportlife.model.modelenum;

import diploma.sportlife.exception.invalid.InvalidSportEventTypeException;
import diploma.sportlife.exception.invalid.InvalidSportEventTypeOpportunitiesException;

public enum SportEventTypeOpportunities {
    ONLY_TRAINING("лише тренування"),
    TRAINING_AND_COMPETITION("тренування та змагання"),
    ALL("усі види разом");

    String displayName;

    SportEventTypeOpportunities(String displayName) {
        this.displayName = displayName;
    }

    public static SportEventTypeOpportunities createWithValue(String type) {
        for (SportEventTypeOpportunities sportEventTypeOpportunities : values()) {
            if (sportEventTypeOpportunities.getDisplayName().equals(type)) {
                return sportEventTypeOpportunities;
            }
        }
        throw new InvalidSportEventTypeOpportunitiesException(type);
    }

    public String getDisplayName() {
        return displayName;
    }
}
