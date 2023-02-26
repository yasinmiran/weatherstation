package weather.displays;

import weather.WeatherState;
import weather.observer.Observer;

public class StatisticsDisplay implements Observer<WeatherState>, DisplayElement {

    private int numReadings;
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;

    @Override
    public void update(WeatherState state) {

        float temp = state.getTemperature();

        tempSum += temp;
        numReadings++;

        if (temp > maxTemp) {
            maxTemp = temp;
        }

        if (temp < minTemp) {
            minTemp = temp;
        }

        display();

    }

    @Override
    public void display() {
        System.out.println(
                "Avg/Max/Min temperature = " +
                        (tempSum / numReadings) + "/"
                        + maxTemp + "/"
                        + minTemp
        );
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
