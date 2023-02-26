package weather;

import weather.observer.Observer;

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
            sendNotification(subscriber);
        }
    }

    @Override
    public void sendNotification(Observer<WeatherState> observer) {
        observer.update(this.state);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        System.out.println("setting new measurements...");
        this.state.setTemperature(temperature);
        this.state.setHumidity(humidity);
        this.state.setPressure(pressure);
        notifyObservers();
    }

    public List<Observer<WeatherState>> getSubscribers() {
        return subscribers;
    }

    public WeatherState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "WeatherStationImpl{" +
                "subscribers=" + subscribers +
                ", state=" + state +
                '}';
    }

}
