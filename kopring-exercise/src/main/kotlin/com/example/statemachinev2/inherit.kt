package com.example.statemachinev2

import ru.nsk.kstatemachine.*

object ExitEvent : Event
object NextEvent : Event


fun main() {
    val machine = createStateMachine("Nested states") {
        logger = StateMachine.Logger { println(it) }

        val state2 = finalState("Finish")

        initialState("State1") {
            transition<ExitEvent>("Exit") { targetState = state2 }

            val state11 = initialState("State1_1")
            val state12 = state("State1_2")
            val state13 = state("State1_3")

            state11 {
                transition<NextEvent> { targetState = state12 }
            }
            state12 {
                transition<NextEvent> { targetState = state13 }
            }
            state13 {
                transition<NextEvent> { targetState = state11 }
            }
        }
    }

    machine.processEvent(NextEvent)
    machine.processEvent(NextEvent)
    machine.processEvent(ExitEvent)

    exportUml(machine, "inherit")
}