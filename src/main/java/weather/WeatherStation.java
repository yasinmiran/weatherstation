package weather;

import observer.Observable;
import observer.Observer;

import java.util.List;

public interface WeatherStation extends Observable<WeatherState> {

    void measurementsChanged();

    void setMeasurements(float temperature, float humidity, float pressure);

    List<Observer<WeatherState>> getSubscribers();

    WeatherState getState();

}
