package be.ducobu.inTime.exception

class ExistingChildFoundException extends RuntimeException {
    ExistingChildFoundException(String childEntity) {
        super("One or more '$childEntity' still linked to this entity.")
    }
}
