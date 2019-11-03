package zuminternet.pilot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PilotApplication

fun main(args: Array<String>) {
    runApplication<PilotApplication>(*args)
}
