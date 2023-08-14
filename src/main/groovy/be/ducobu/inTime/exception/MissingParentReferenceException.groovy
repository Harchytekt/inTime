package be.ducobu.inTime.exception

class MissingParentReferenceException extends RuntimeException {
    MissingParentReferenceException(String entity, String parentName) {
        super("Missing parent reference '$parentName' (id or name) to create the entity '${entity}'!")
    }
}
