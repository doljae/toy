package com.example.statemachinev2

import ru.nsk.kstatemachine.*
import ru.nsk.kstatemachine.visitors.exportToPlantUml
import java.io.File
import java.nio.file.Paths

object SwitchEvent : Event

fun main() {
    val machine = createStateMachine("nested state") {
        val state1 = initialState("State1")
        val state3 = finalState("State3")

        val state2 = state("State2") {
            transition<SwitchEvent> { targetState = state3 }
            transition<SwitchEvent>("back") { targetState = state1 }

            val finalState = finalState("Final subState")

            initialState("initial substate") {
                transition<SwitchEvent> { targetState = finalState }
            }
        }

        state1 {
            transition<SwitchEvent>("to ${state2.name}") { targetState = state2 }
            transition<SwitchEvent> { targetState = this@state1 }
            transition<SwitchEvent>()
        }
    }
    val path = Paths.get("").toAbsolutePath().toString()
    val exportToPlantUml = machine.exportToPlantUml()
    File("$path/nested.puml").writeText(exportToPlantUml)

}