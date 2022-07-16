package com.example.whiteboard.function

open class Fruit
class Apple : Fruit()
class Banana : Fruit()

// Array 는 mutable, List는 immutable
fun receiveFruitsV0(fruits: Array<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

// immutable하기 때문에 write가 없다는 것을 보장해서 위 메서드가 컴파일 에러가 없음
fun receiveFruits(fruits: List<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

// read-only는 안에 있는 값을 빼서(out) 읽고, write-only는 새로운 값을 넣어야(in) 한다
fun copyFromTo(from: Array<out Fruit>, to: Array<in Fruit>) {
    for (i in from.indices) {
        to[i] = from[i]
    }
}

// T에 대한 제약 조건을 한개면 : 를 사용해 명시할 수 있다
fun <T : AutoCloseable> useAndClose(input: T) {
    // compile error, T가 close()를 가지고 있는지 명시해줘야함
    // input.close

    input.close()
}

// 2개 이상일 때는 where를 사용한다
fun <T> useAndCloseV2(input: T) where T : AutoCloseable, T : Appendable {
    input.append("there")
    input.close()
}

// java에 ? 와 비슷한 *
// 타입을 정확히는 모르지만 타입 안정성을 유지하고 읽기만 허용할 때 사용함
fun printValues(values: Array<*>) {
    // 읽기만 가능
    for (value in values) {
        println(value)
    }

    // 쓰기는 불가능
    // values[0] = values[1]
}

// verbose
fun <T> findFirst(fruits: List<Fruit>, ofClass: Class<T>): T {
    val selected = fruits.filter { fruit -> ofClass.isInstance(fruit) }
    if (selected.isEmpty()) {
        throw RuntimeException()
    }
    return ofClass.cast(selected[0])
}

// use inline & reified
// Class<T> 같은건 바이트 코드 컴파일 시 지워진다. 그래서 파라미터의 형태로 넘거야 내부 로직을 처리할 수 있는데...
// inline을 이용해 컴파일 타임에 확정하고 reified를 이용해 클래스 타입 정보를 코드에서 사용한다
// 즉 런타임에서 사라지는 클래스 정보를 사용할 수 있게 되어 메서드에서 타입 검사 같은걸 할 수 있음
inline fun <reified T> findFirst(fruits: List<Fruit>): T {
    val selected = fruits.filter { fruit -> fruit is T }
    if (selected.isEmpty()) {
        throw RuntimeException()
    }
    return selected[0] as T
}

fun main() {
    val arrayOf: List<Apple> = listOf(Apple())
    receiveFruits(arrayOf)

    val basket1 = Array<Fruit>(3) { Apple() }
    val basket2 = Array<Any>(3) { Any() }
    copyFromTo(basket1, basket2)
}