package com.example

import com.example.statemachine.Events
import com.example.statemachine.States
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.statemachine.StateMachine

@SpringBootApplication
class KopringExerciseApplication(private val stateMachine:StateMachine<States,Events>) : CommandLineRunner {
    override fun run(vararg args: String?) {
        stateMachine.sendEvent(Events.E1)
        stateMachine.sendEvent(Events.E2)
    }

}

fun main(args: Array<String>) {
    runApplication<KopringExerciseApplication>(*args)
}
