package be.ducobu.inTime.rest

import be.ducobu.inTime.exception.WrongRestCallException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class FallbackRestController {

    @GetMapping("/**")
    void allFallback() {
        throw new WrongRestCallException()
    }
}
