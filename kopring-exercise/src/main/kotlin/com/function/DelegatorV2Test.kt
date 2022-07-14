package com.function

// delegator 단순 구현
// 단점 1, JavaProgrammer에게만 요청할 수 있다
// 단점 2, ManagerOldVersion 내부에서 JavaProgrammer()를 가리키는 delegator에 접근할 수 없다
class ManagerOldVersion : Worker by JavaProgrammer()

// 개선 버전
class ManagerNewVersion(val staff: Worker) : Worker by staff {
    // 생성자로 Worker를 넣어주고 사용하면 staff라는 delegator를 내부에서 접근할 수 있다.
    fun meeting() = println("organizing meeting with ${staff.javaClass.simpleName}")

    // delegator의 메서드를 override 해서 사용해 해당 클래스에서 정의한 메서드를 사용할 수 있다.
    override fun takeVacation() {
        println("manager vacation")
    }
}

// var로 Worker를 받음
class ManagerNewVersionWithDefect(var staff: Worker) : Worker by staff {
    // 생성자로 Worker를 넣어주고 사용하면 staff라는 delegator를 내부에서 접근할 수 있다.
    fun meeting() = println("organizing meeting with ${staff.javaClass.simpleName}")

    // delegator의 메서드를 override 해서 사용해 해당 클래스에서 정의한 메서드를 사용할 수 있다.
    override fun takeVacation() {
        println("manager vacation")
    }
}

fun main() {
    val managerWithJavaProgrammer = ManagerNewVersion(JavaProgrammer())
    val managerWithPythonProgrammer = ManagerNewVersion(PythonProgrammer())
    managerWithJavaProgrammer.meeting()
    managerWithJavaProgrammer.work()
    managerWithPythonProgrammer.meeting()
    managerWithPythonProgrammer.work()

    // manager vacation
    managerWithJavaProgrammer.takeVacation()

    // delegation 문법은 decorator가 아니다. 즉 delegator 타입을 대체할 순 없다
    // compile error
    // val coder: JavaProgrammer = managerWithJavaProgrammer
    val employee1: Worker = managerWithJavaProgrammer
    val employee2: Worker = managerWithPythonProgrammer

    // delegation 문법에 선언하는 delegator는 property가 아니라 parameter 취급한다
    // val이 아닌 var로 선언하면 갈아낄수 있지만 메서드는 최초 선언한 delegator의 메서드를 수행한다
    // 갈아껴지긴 했지만 최초 선언한 delegator에 접근할 수 없기 때문에 memory leak이 발생할 수 있다.
    // val을 쓰자

    println(" ================= ")
    val manager = ManagerNewVersionWithDefect(JavaProgrammer())

    println("Staff is ${manager.staff.javaClass.simpleName}")
    println(manager.work())

    manager.staff = PythonProgrammer()
    println("Staff is ${manager.staff.javaClass.simpleName}")
    manager.work() // code with Java -> defect
}