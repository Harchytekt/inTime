package be.ducobu.inTime.exception

class AlreadyRunningTimeEntryException extends RuntimeException {

    AlreadyRunningTimeEntryException() {
        super("A 'Time Entry' is already running!")
    }

}
