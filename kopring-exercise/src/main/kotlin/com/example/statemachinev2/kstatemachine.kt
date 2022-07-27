package com.example.statemachinev2

import ru.nsk.kstatemachine.StateMachine
import ru.nsk.kstatemachine.visitors.exportToPlantUml
import java.io.File
import java.nio.file.Paths

fun exportUml(machine: StateMachine, fileName: String) {
    val path = Paths.get("").toAbsolutePath().toString()
    val exportToPlantUml = machine.exportToPlantUml()
    File("$path/$fileName.puml").writeText(exportToPlantUml)
}