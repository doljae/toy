package com.designpattern.observer

class WeatherData(
    var temperature: Float,
    var humidify: Float,
    var pressure: Float,
    val observers: MutableList<Observer>
) : Subject {


    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.update(temperature, humidify, pressure)
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