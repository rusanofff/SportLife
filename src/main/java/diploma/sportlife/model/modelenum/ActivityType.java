package diploma.sportlife.model.modelenum;

import diploma.sportlife.exception.invalid.InvalidActivityTypeException;
import diploma.sportlife.exception.invalid.InvalidSportEventTypeException;

public enum ActivityType {
    PERSONAL("особистий"),
    TEAM("командний"),
    ALL("особистий/командний"),
    NULLABLE(null);

    String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    public static ActivityType createWithValue(String type) {
        for (ActivityType activityType : values()) {
            if (activityType.toString().equals(type)) {
                return activityType;
            }
        }
        throw new InvalidActivityTypeException(type);
    }

    public String getDisplayName() {
        return displayName;
    }
}
