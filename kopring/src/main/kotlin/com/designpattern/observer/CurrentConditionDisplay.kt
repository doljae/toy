package com.designpattern.observer

class CurrentConditionDisplay(
    var weatherData: WeatherData
) : DisplayElement, Observer {
    var temperature: Float = 0F
    var humidity: Float = 0F

    init {
        this.weatherData = weatherData
        weatherData.registerObserver(this)
    }

    override fun display() {
        println("현재 상태: 온도 " + temperature + "F, 습도 " + humidity + "%")
    }

    override fun update(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        display()
    }
}