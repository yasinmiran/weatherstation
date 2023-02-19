package interceptor;

import java.lang.reflect.Method;

/**
 * Abstract interceptor that describes an out-of-band service.
 *
 * @author yasin
 * @see ValidSubscriberInterceptor
 */
public interface Interceptor {

    void onPreMarshalRequest(Object obj, Method method, Object[] args);

    void onPostMarshalRequest(Object obj, Method method, Object[] args);

}
