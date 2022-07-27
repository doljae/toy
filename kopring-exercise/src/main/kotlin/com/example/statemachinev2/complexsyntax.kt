package com.example.statemachinev2

import ru.nsk.kstatemachine.*

object SwitchYellowEvent : Event

class SwitchRedEvent(val info: String) : Event

class YellowState(val info: Int) : DefaultState("Yellow")

fun main() {
    val machine = createStateMachine("traffic lights") {
        val greenState = initialState("Green")
        val yellowState = addState(YellowState(42))
        val redState = finalState("Red")

        greenState {
            onEntry { println("1 Enter $name state") }
            onExit { println("Ext $name state") }

            transition<SwitchYellowEvent> {
                targetState = yellowState
                onTriggered { println("Switching to $targetState, with argument: ${it.argument}") }
            }

            transition<SwitchRedEvent> {
                guard = { false }
                targetState = redState
            }

            transitionOn<SwitchRedEvent> {
                guard = { false }
                targetState = {
                    val condition = true
                    if (condition) redState else greenState
                }
            }
        }

        yellowState {
            transitionConditionally<SwitchRedEvent> {
                direction = {
                    fun getCondition() = 0

                    when (getCondition()) {
                        0 -> targetState(redState)
                        1 -> targetState(greenState)
                        2 -> stay()
                        else -> noTransition()
                    }
                }

                onTriggered {
                    println("Switching state with info: ${this@yellowState.info}")
                }
            }
        }

//        logger = StateMachine.Logger { println(it) }

        ignoredEventHandler =
            StateMachine.IgnoredEventHandler { event, argument ->
                error(
                    "$this can not process pending $event " +
                            "as event processing is already running. " +
                            "Do not call processEvent() from notification listeners."
                )
            }

        onStarted {
            println("$name started")
        }
    }

    with(machine) {
        onTransition {
            println(
                "Transition from ${it.transition.sourceState} to ${it.direction.targetState} " +
                        "on ${it.event} with argument: ${it.argument}"
            )
        }

        onStateChanged { println("State changed to $it") }
        onFinished { println("$name finished") }
    }

    // access state
    val greenState = machine.requireState("Green")
    greenState.onEntry { println("Green state entry") }

    // access transition
    val requireTransition = greenState.requireTransition<SwitchYellowEvent>()
    requireTransition.onTriggered { println("${it.transition} triggered") }


    machine.processEvent(SwitchYellowEvent, "Get Ready")
//    machine.processEvent(SwitchRedEvent("Stop!"))

    exportUml(machine, "complexUsage")
}