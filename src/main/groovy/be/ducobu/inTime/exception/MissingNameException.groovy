package be.ducobu.inTime.exception

class MissingNameException extends RuntimeException {
    MissingNameException(String entity, String field="name") {
        super("Missing the field '$field' to create the entity '${entity}'!")
    }
}
