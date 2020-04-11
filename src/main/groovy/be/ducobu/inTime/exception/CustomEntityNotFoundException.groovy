package be.ducobu.inTime.exception

import javax.persistence.EntityNotFoundException

class CustomEntityNotFoundException extends EntityNotFoundException {

    CustomEntityNotFoundException(String entityName, String entityAttribute) {
        super("No '${entityName}' with attribute '${entityAttribute}' found!")
    }

}
