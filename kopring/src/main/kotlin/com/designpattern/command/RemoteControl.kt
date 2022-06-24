package com.designpattern.command

class RemoteControl() {
    val onCommands: MutableList<Command> = mutableListOf()
    val offCommands: MutableList<Command> = mutableListOf()

    init {
        for (i in 0..7) {
            onCommands.add(NoCommand())
            offCommands.add(NoCommand())
        }
    }

    fun setCommand(slot: Int, onCommand: Command, offCommand: Command) {
        onCommands[slot] = onCommand
        offCommands[slot] = offCommand
    }

    fun onButtonWasPushed(slot: Int) {
        onCommands[slot].execute()
    }

    fun offButtonWasPushed(slot: Int) {
        offCommands[slot].execute()
    }

    override fun toString() = "리모컨, ${onCommands.joinToString()}, ${offCommands.joinToString()}"
}

fun main() {
    val remoteControl = RemoteControl()

    val lightOnCommand = LightOnCommand(Light())
    val lightOffCommand = LightOffCommand(Light())

    val garageDoorUpCommand = GarageDoorUpCommand()
    garageDoorUpCommand.garageDoor = GarageDoor()
    val garageDoorDownCommand = GarageDoorDownCommand()
    garageDoorDownCommand.garageDoor = GarageDoor()

    remoteControl.setCommand(0, lightOnCommand, lightOffCommand)
    remoteControl.setCommand(1, garageDoorUpCommand, garageDoorDownCommand)

    println(remoteControl)

    remoteControl.onButtonWasPushed(0)
    remoteControl.offButtonWasPushed(0)
    remoteControl.onButtonWasPushed(1)
    remoteControl.offButtonWasPushed(1)
}