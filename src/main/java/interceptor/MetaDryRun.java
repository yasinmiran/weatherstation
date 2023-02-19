package interceptor;

import observer.Observer;
import weather.WeatherState;
import weather.WeatherStation;
import weather.WeatherStationImpl;
import weather.displays.ForecastDisplay;
import weather.displays.HeatIndexDisplay;

import java.lang.reflect.Proxy;

public class MetaDryRun {

    public static void main(String[] args) {

        final WeatherStationImpl station = new WeatherStationImpl();
        final InterceptorProxy proxy = new InterceptorProxy(new WeatherStationImpl());

        WeatherStation weatherStation = (WeatherStation) Proxy.newProxyInstance(
                station.getClass().getClassLoader(),
                station.getClass().getInterfaces(),
                proxy
        );

        InterceptorRegistry.register(WeatherStationImpl.class, new ValidSubscriberInterceptor());

        Observer<WeatherState> forecastDisplay = new ForecastDisplay();
        Observer<WeatherState> heatIndexDisplay = new HeatIndexDisplay();

        weatherStation.register(forecastDisplay);
        weatherStation.register(heatIndexDisplay);

        weatherStation.setMeasurements(80, 65, 30.4f);
        weatherStation.setMeasurements(82, 70, 29.2f);
        weatherStation.setMeasurements(78, 90, 29.2f);

    }


}
