package be.ducobu.inTime.exception

import javax.persistence.EntityNotFoundException
import java.text.MessageFormat

class CustomEntityNotFoundException extends EntityNotFoundException {

    CustomEntityNotFoundException(String entityName, Long entityId) {
        super(MessageFormat.format("No ''{0}'' with id {1} found!", entityName, entityId))
    }

}
