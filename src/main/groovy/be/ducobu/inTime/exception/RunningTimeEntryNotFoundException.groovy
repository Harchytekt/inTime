package be.ducobu.inTime.exception

import javax.persistence.EntityNotFoundException

class RunningTimeEntryNotFoundException extends EntityNotFoundException {

    RunningTimeEntryNotFoundException() {
        super("No running 'Time Entry' found!")
    }

}
