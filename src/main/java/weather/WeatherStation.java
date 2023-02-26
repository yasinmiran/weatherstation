package weather;


import framework.Intercepted;
import interceptors.LoggingInterceptor;
import weather.observer.Observable;
import weather.observer.Observer;

import java.util.List;

public interface WeatherStation extends Observable<WeatherState> {

    void sendNotification(Observer<WeatherState> observer);

    @Intercepted(handler = LoggingInterceptor.class)
    void setMeasurements(float temperature, float humidity, float pressure);

    List<Observer<WeatherState>> getSubscribers();

    WeatherState getState();

}
