package framework;

import java.lang.reflect.Method;

/**
 * Abstract interceptor that describes an
 * out-of-band service.
 *
 * @author yasin
 * @see interceptors.LoggingInterceptor
 */
public interface Interceptor {

    void onPreMarshalRequest(Object context, Object proxy,
                             Method callback, Object[] args) throws Exception;

    void onPostMarshalRequest(Object context, Object proxy,
                              Method callback, Object[] arg) throws Exception;

}
