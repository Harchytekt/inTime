package be.ducobu.inTime.exception

import javax.persistence.EntityNotFoundException
import java.text.MessageFormat

class CustomEntityNotFoundException extends EntityNotFoundException {

    CustomEntityNotFoundException(String entityName, String entityAttribute) {
        super(MessageFormat.format("No ''{0}'' with attribute ''{1}'' found!", entityName, entityAttribute))
    }

}
