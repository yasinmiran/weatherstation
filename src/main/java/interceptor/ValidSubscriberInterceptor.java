package interceptor;

import java.lang.reflect.Method;

public class ValidSubscriberInterceptor implements Interceptor {

    @Override
    public void onPreMarshalRequest(Object obj, Method method, Object[] args) {
        System.out.println("Before method execution");
    }

    @Override
    public void onPostMarshalRequest(Object obj, Method method, Object[] args) {
        System.out.println("After method execution");
    }

}
