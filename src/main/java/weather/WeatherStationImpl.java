package weather;

import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationImpl implements WeatherStation {

    private final List<Observer<WeatherState>> subscribers;
    private final WeatherState state;

    public WeatherStationImpl() {
        this.subscribers = new ArrayList<>();
        this.state = new WeatherState();
    }

    @Override
    public void register(Observer<WeatherState> observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void unregister(Observer<WeatherState> observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<WeatherState> subscriber : this.subscribers) {
            subscriber.update(this.state);
        }
    }

    @Override
    public void measurementsChanged() {
        notifyObservers();
    }

    @Override
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.state.setTemperature(temperature);
        this.state.setHumidity(humidity);
        this.state.setPressure(pressure);
        measurementsChanged();
    }

    @Override
    public List<Observer<WeatherState>> getSubscribers() {
        return subscribers;
    }

    @Override
    public WeatherState getState() {
        return state;
    }

}
