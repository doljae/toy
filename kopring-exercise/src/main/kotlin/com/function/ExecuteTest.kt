package com.example.whiteboard.function

import com.jaredrummler.ktsh.Shell
import java.io.File
import java.util.concurrent.TimeUnit

fun String.execute(
    workingDir: File = File("./"),
    timeout: Long = 60L, unit: TimeUnit = TimeUnit.SECONDS
): String {
    val proc = ProcessBuilder(this.split("\\s".toRegex()))
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()
    with(proc) {
        if (!waitFor(timeout, unit)) {
            destroy()
            throw RuntimeException("execution timed out: $this")
        }
        if (exitValue() != 0) {
            throw RuntimeException("execution failed with code ${exitValue()}: $this")
        }
        return inputStream.bufferedReader().readText()
    }
}

fun main() {
//    println("git status".execute())

    val shell = Shell("sh")
    val result = shell.run("git status")
    if (result.isSuccess) {
        println(result.stdout())
    }
    shell.shutdown()
}