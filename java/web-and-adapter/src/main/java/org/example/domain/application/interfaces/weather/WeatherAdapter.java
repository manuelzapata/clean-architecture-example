package org.example.domain.application.interfaces.weather;

import org.example.domain.model.Weather;

public interface WeatherAdapter {

    Weather getWeather(String location);

}