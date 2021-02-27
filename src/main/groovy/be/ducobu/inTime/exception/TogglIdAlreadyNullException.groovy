package be.ducobu.inTime.exception

class TogglIdAlreadyNullException extends RuntimeException {
    TogglIdAlreadyNullException(String entity, Long id) {
        super("There is no Toggl ID linked to the entity '$entity' with id '$id'!")
    }
}
