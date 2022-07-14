package com.designpattern.command

class GarageDoorDownCommand : Command {

    lateinit var garageDoor: GarageDoor

    override fun execute() {
        garageDoor.down()
    }
}