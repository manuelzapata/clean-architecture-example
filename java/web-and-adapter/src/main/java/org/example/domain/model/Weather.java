package org.example.domain.model;

public class Weather {
    private double temperature;
    private double humidity;
    private double pressure;

    public Weather(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    @Override
    public String toString() {
        return String.format("Temperature: %.2f°C, Humidity: %.2f%%, Pressure: %.2fhPa", temperature, humidity, pressure);
    }
}

