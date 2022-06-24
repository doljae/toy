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

    override fun toString() = "리모컨, $onCommands, $offCommands"
}