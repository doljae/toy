package com.example.statemachinev2

import ru.nsk.kstatemachine.*

data class LoginData(val email: String, val password: String)

class LoginEvent(override val data: LoginData) : DataEvent<LoginData>

fun checkUserPassword(data: LoginData) = data.password == "qwerty"

fun main() {
    val machine = createStateMachine("Data states") {
        logger = StateMachine.Logger { println(it) }

        val accountFormState = dataState<LoginData>("accountForm") {
            onEntry { println("login with :$data") }
        }

        initialState("loginForm") {
            dataTransition<LoginEvent, LoginData> {
                guard = { checkUserPassword(it.data) }
                targetState = accountFormState
            }
        }
    }

    machine.processEvent(LoginEvent(LoginData("test@gmail.com", "qwerty")))

    exportUml(machine, "typesafetransition")
}