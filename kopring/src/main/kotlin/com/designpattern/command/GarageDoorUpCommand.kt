package com.designpattern.command

class GarageDoorUpCommand : Command {

    lateinit var garageDoor: GarageDoor

    override fun execute() {
        garageDoor.up()
    }
}