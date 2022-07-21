package com.designpattern.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class OwnerClassInvocationHandler(var person: Person) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        try {
            if (method!!.name.startsWith("get")) {
                return method.invoke(person, args)
            } else if (method.name.equals("setGeekRating")) {
                throw IllegalArgumentException()
            } else if (method.name.startsWith("set")) {
                return method.invoke(person, args)
            }
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return null
    }
}