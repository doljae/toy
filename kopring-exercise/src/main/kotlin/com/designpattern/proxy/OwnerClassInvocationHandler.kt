package com.designpattern.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

// https://stackoverflow.com/questions/41774450/why-is-kotlin-throw-illegalargumentexception-when-using-proxy
class OwnerClassInvocationHandler(var person: Person) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        try {
            if (method!!.name.isNullOrBlank()) {
                return null
            }
            if (method.name.startsWith("get")) {
                return method.invoke(person, *args.orEmpty())
            } else if (method.name.equals("setGeekRating")) {
                throw IllegalArgumentException()
            } else if (method.name.startsWith("set")) {
                return method.invoke(person, *args.orEmpty())
            }
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return null
    }
}

fun main() {
    // 실제 객체
    val tom = PersonImpl("tom", "male", "coding", 100, 5)

    // 프록시 객체
    val tomProxy = Proxy.newProxyInstance(
        tom.javaClass.classLoader,
        tom.javaClass.interfaces,
        OwnerClassInvocationHandler(tom)
    ) as Person

    println("이름: ${tomProxy.getName()}")

    tomProxy.setInterests("bowling")

    try {
        tomProxy.setGeekRating(10)
    } catch (e: Exception) {
        println("본인 프록시에는 괴짜 지수를 매길 수 없습니다")
    }

    println("괴짜 지수: ${tomProxy.getGeekRating()}")

}