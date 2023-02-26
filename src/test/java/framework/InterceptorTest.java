package framework;

import interceptors.LoggingInterceptor;
import org.junit.jupiter.api.Test;
import weather.WeatherStation;
import weather.WeatherStationImpl;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterceptorTest {

    @Test
    public void itShouldInterceptMethodBeforeInvocation() {

        WeatherStation context = new WeatherStationImpl();

        WeatherStation proxy = (WeatherStation) Proxy.newProxyInstance(
                context.getClass().getClassLoader(),
                context.getClass().getInterfaces(),
                /*Dispatcher*/ (dynamicProxy, method, args) -> {

                    // check whether this is the exact method
                    assertEquals("setMeasurements", method.getName());

                    // check whether the forwarded arguments are in order
                    assertArrayEquals(new Object[]{80f, 65f, 30.4f}, args);

                    // check whether the existing state is tampered
                    assertEquals(0f, context.getState().getTemperature());
                    assertEquals(0f, context.getState().getHumidity());
                    assertEquals(0f, context.getState().getPressure());

                    // invoke the actual callback
                    method.invoke(context, args);

                    // check the newly updated state
                    assertEquals(80f, context.getState().getTemperature());
                    assertEquals(65f, context.getState().getHumidity());
                    assertEquals(30.4f, context.getState().getPressure());

                    return null;

                }
        );

        InterceptorRegistry.register(context.getClass(), new LoggingInterceptor());

        proxy.setMeasurements(80f, 65f, 30.4f);

    }

}