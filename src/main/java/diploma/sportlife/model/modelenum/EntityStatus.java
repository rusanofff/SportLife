package diploma.sportlife.model.modelenum;

import diploma.sportlife.exception.invalid.InvalidStatusException;

public enum EntityStatus {
    ENABLE,
    DISABLE;


    public static EntityStatus createWithValue(String status) {
        if (status == null) {
            return EntityStatus.ENABLE;
        }
        for (EntityStatus entityStatus : values()) {
            if (entityStatus.name().equals(status)) {
                return entityStatus;
            }
        }

        throw new InvalidStatusException(status);
    }
}
