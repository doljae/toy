package com.designpattern.command

class SimpleRemoteControl {

    lateinit var slot: Command

    fun buttonWasPressed() {
        slot.execute()
    }
}

fun main() {
    val remote = SimpleRemoteControl()
    val light = Light()
    val lightOnCommand = LightOnCommand(light)

    remote.slot = lightOnCommand
    remote.buttonWasPressed()

    val garageDoor = GarageDoor()
    val garageDoorUpCommand = GarageDoorUpCommand()
    garageDoorUpCommand.garageDoor = garageDoor

    remote.slot = garageDoorUpCommand
    remote.buttonWasPressed()
}