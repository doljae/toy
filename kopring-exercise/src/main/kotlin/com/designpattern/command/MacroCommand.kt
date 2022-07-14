package com.designpattern.command

class MacroCommand(val commands: MutableList<Command> = mutableListOf()) : Command {

    override fun execute() {
        for (command in commands) {
            command.execute()
        }
    }
}