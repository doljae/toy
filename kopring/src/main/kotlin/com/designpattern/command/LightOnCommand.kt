package com.designpattern.command

class LightOnCommand(val light: Light) : Command {

    override fun execute() {
        light.on()
    }
}