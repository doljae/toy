package com.designpattern.observer

class WeatherData(
    var temperature: Float = 0F,
    var humidify: Float = 0F,
    var pressure: Float = 0F,
    val observers: MutableList<Observer> = mutableListOf()
) : Subject {


    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            // observer.update(temperature, humidify, pressure)
            observer.update()
        }
    }

    fun measurementsChanged() {
        notifyObservers()
    }

    fun setMeasurements(temperature: Float, humidify: Float, pressure: Float) {
        this.temperature = temperature
        this.humidify = humidify
        this.pressure = pressure
        measurementsChanged()
    }
}