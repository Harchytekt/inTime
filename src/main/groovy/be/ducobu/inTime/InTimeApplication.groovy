package be.ducobu.inTime

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
class InTimeApplication {

    static void main(String[] args) {
        SpringApplication.run(InTimeApplication, args)
    }

}
