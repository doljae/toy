package com.example.statemachinev2

import ru.nsk.kstatemachine.*

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
        addInitialState(States.GreenState) {
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
    }

    machine.processEvent(Events.NextEvent)
    machine.processEvent(Events.NextEvent)

    exportUml(machine, "minimalSealed")
}