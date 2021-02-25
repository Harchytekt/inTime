package be.ducobu.inTime.exception

class NotModifiedEntityException extends RuntimeException {

    NotModifiedEntityException(String entityName, String entityAttribute, String message="Please check the changes you've made.") {
        super("The entity '${entityName}' with attribute '${entityAttribute}' couldn't be updated! $message")
    }

}
