package be.ducobu.inTime.rest

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class FallbackRestController {

//    @GetMapping("/**")
//    void allFallback() {
//        throw new WrongRestCallException()
//    }
}
