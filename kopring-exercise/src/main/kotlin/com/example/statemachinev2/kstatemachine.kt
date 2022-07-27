package com.example.statemachinev2

import ru.nsk.kstatemachine.*
import ru.nsk.kstatemachine.visitors.exportToPlantUml
import java.io.File
import java.nio.file.Paths

sealed class Events {
    object NextEvent : Event
}

sealed class States {
    object GreenState : DefaultState()
    object YellowState : DefaultState()
    object RedState : DefaultFinalState()
}

fun main() {
    val machine = createStateMachine {
        addState(States.GreenState) {
            onEntry { println("Enter green") }
            onExit { println("Exit green") }

            transition<Events.NextEvent> {
                targetState = States.YellowState
                onTriggered { println("Transition triggered") }

            }
        }
        addState(States.YellowState) {
            transition<Events.NextEvent> {
                targetState = States.YellowState
                onTriggered { println("Transition triggered") }
            }
        }

        addFinalState(States.RedState)

        onFinished { println("finished") }

        setInitialState(States.YellowState)

    }

    machine.processEvent(Events.NextEvent)
    machine.processEvent(Events.NextEvent)
}

fun exportUml(machine: StateMachine, fileName: String) {
    val path = Paths.get("").toAbsolutePath().toString()
    val exportToPlantUml = machine.exportToPlantUml()
    File("$path/$fileName.puml").writeText(exportToPlantUml)
}