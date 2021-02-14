package be.ducobu.inTime.exception

class NoEntryFoundException extends RuntimeException {
    NoEntryFoundException(String entity) {
        super("No entity '${entity}' found!")
    }
}
