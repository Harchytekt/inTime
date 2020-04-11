package be.ducobu.inTime.exception

import javax.persistence.EntityNotFoundException

class WrongRestCallException extends EntityNotFoundException {
    WrongRestCallException() {
        super("Wrong REST call!")
    }
}
