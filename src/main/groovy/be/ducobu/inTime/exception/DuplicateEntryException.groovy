package be.ducobu.inTime.exception

class DuplicateEntryException extends RuntimeException {
    DuplicateEntryException(String entity, String attribute, String entry) {
        super("An entity '${entity}' with '${attribute}' '${entry}' already exist!")
    }
}
