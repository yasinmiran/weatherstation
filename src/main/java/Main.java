import framework.Dispatcher;
import framework.InterceptorRegistry;
import interceptors.LoggingInterceptor;
import weather.WeatherStation;
import weather.WeatherStationImpl;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        // the implementation of the weather station
        // using the observer design pattern as per the
        // pattern oriented software architecture book.
        WeatherStation context = new WeatherStationImpl();

        // first we create the dispatcher, and we pass the
        // context object (object that is intercepted)
        // to it so that the concrete interceptor can access
        // its instance.
        Dispatcher dispatcher = new Dispatcher(context);

        // we create a proxy for the context object using the
        // Proxy provided by Java SDK. Whenever, a method is
        // invoked on this wrapped proxy it calls the dispatcher
        // and the dispatcher checks whether it is actually
        // intercepted or not. if yes, it intercepts and forwards
        // method to the actual callee.
        WeatherStation proxy = (WeatherStation) Proxy.newProxyInstance(
                context.getClass().getClassLoader(),
                context.getClass().getInterfaces(),
                dispatcher
        );

        // register the interceptor
        InterceptorRegistry.register(WeatherStationImpl.class, new LoggingInterceptor());

        // we need to log these arguments whenever we call
        // this setMeasurements(t, h, p) method. ideally
        // intercepted via a proxy.
        proxy.setMeasurements(80, 65, 30.4f);
        proxy.setMeasurements(82, 70, 29.2f);
        proxy.setMeasurements(78, 90, 29.2f);

    }

}
