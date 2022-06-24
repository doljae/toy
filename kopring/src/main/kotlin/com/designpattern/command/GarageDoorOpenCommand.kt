package com.designpattern.command

class GarageDoorOpenCommand : Command {

    lateinit var garageDoor: GarageDoor

    override fun execute() {
        garageDoor.up()
    }
}