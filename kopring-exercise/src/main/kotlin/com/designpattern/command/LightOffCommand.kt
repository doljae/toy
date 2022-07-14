package com.designpattern.command

class LightOffCommand(val light: Light) : Command {

    override fun execute() {
        light.off()
    }
}