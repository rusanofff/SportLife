package diploma.sportlife.model.modelenum;

import diploma.sportlife.exception.invalid.InvalidSportEventTypeException;

public enum SportEventType {
    TRAINING("тренування"),
    GAME("гра"),
    COMPETITION("змагання");

    String displayName;

    SportEventType(String displayName) {
        this.displayName = displayName;
    }

    public static SportEventType createWithValue(String type) {
        for (SportEventType sportEventParticipants : values()) {
            if (sportEventParticipants.toString().equals(type)) {
                return sportEventParticipants;
            }
        }
        throw new InvalidSportEventTypeException(type);
    }

    public String getDisplayName() {
        return displayName;
    }
}
