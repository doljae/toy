package com.example.statemachinev2

import ru.nsk.kstatemachine.*

object SwitchEvent2 : Event

fun main() {
    val machine = createStateMachine {
        logger = StateMachine.Logger { println(it) }

        lateinit var nested22: State

        initialState("Top level 1") {
            initialState("Nested 11") {
                // Target state is declared in another state
                transitionOn<SwitchEvent2> { targetState = { nested22 } }
            }
        }

        state("Top level 2") {
            initialState("Nested 21")
            nested22 = state("Nested 22")
        }
    }

    machine.processEvent(SwitchEvent2)

    exportUml(machine, "crossleveltransition")
}