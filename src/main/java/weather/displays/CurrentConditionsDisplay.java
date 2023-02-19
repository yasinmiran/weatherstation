package weather.displays;

import observer.Observer;
import weather.WeatherState;

public class CurrentConditionsDisplay implements Observer<WeatherState>, DisplayElement {

    private float temperature;
    private float humidity;

    @Override
    public void update(WeatherState weatherState) {
        this.temperature = weatherState.getTemperature();
        this.humidity = weatherState.getHumidity();
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }

}
