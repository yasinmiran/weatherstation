package weather.displays;

import observer.Observer;
import weather.WeatherState;

public class ForecastDisplay implements Observer<WeatherState>, DisplayElement {

    private float currentPressure = 29.92f;
    private float lastPressure;

    @Override
    public void update(WeatherState state) {
        lastPressure = currentPressure;
        currentPressure = state.getPressure();
        display();
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }

}
