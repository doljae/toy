package com.function

interface Worker {
    fun work()
    fun takeVacation()
}

open class JavaProgrammer : Worker {
    override fun work() {
        println("code with Java")
    }

    override fun takeVacation() {
        println("code at the beach")
    }
}

open class PythonProgrammer : Worker {
    override fun work() {
        println("code with Python")
    }

    override fun takeVacation() {
        println("code at the home")
    }
}

class Manager : JavaProgrammer()
// Manager가 필드 값으로 JavaProgrammer를 가지고 있도록 하면 상속은 안해도 되지만...
// JavaProgrammer의 메서드가 많아지면 내부적으로 다 메서드를 선언해줘야함
// boiler-plate 코드가 계속 생김
class ManagerV2(val worker: JavaProgrammer) {
    fun work() = worker.work()
    fun takeVacation() = worker.work()
}

// delegation을 문법적으로 지원한다
// 별도의 코딩 없이 JavaProgrammer의 메서드들을 ManagerV3는 호출할 수 있게 된다
class ManagerV3() : Worker by JavaProgrammer()

fun main() {
    val manager = Manager()
    // JavaProgrammer를 상속해서 JavaProgrammer의 메서드를 사용
    manager.work()

    // coder는 Manager는 아니지만 Manager가 JavaProgrammer를 상속하고 있어서 아래와 같은 초기화도 가능해짐, 단점
    val coder: JavaProgrammer = manager

    // JavaProgrammer를 생성자로 전달해서 해결은 가능
    val managerV2 = ManagerV2(JavaProgrammer())
    managerV2.worker

    // delegation 적용
    val managerV3 = ManagerV3()
    // 따로 아무것도 작성해주지 않았는데 정상적으로 컴파일된다
    managerV3.work()
    // 상속을 사용하지 않기 때문에 아래 코드는 컴파일 에러가 발생한다
    // val coderV2: JavaProgrammer = managerV3

}
