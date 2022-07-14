package com.designpattern.observer

fun main() {
    val weatherData = WeatherData()

    val currentConditionDisplay = CurrentConditionDisplay(weatherData)

    weatherData.setMeasurements(80F, 65F, 30.4F)
    weatherData.setMeasurements(82F, 70F, 29.2F)
    weatherData.setMeasurements(78F, 90F, 29.2F)
}