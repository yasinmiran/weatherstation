package framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * This is the Dispatcher that is responsible for invoking
 * the intercepted method and forwarding the call to the
 * appropriate interceptor. The Dispatcher acts as an
 * intermediary between the client code, which calls the
 * intercepted method, and the interceptors, which modify
 * or intercept the behavior of the method.
 *
 * @apiNote InterceptorProxy
 */
public class Dispatcher implements InvocationHandler {

    private final Object context;

    public Dispatcher(Object context) {
        this.context = context;
    }

    /**
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance. The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return Return value of the invocation method.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        Object result = null;

        if (method.getAnnotation(Intercepted.class) != null) {
            List<Interceptor> interceptors = InterceptorRegistry.getInterceptors(context.getClass());
            for (Interceptor interceptor : interceptors) {
                if (method.getAnnotation(Intercepted.class).handler()
                        .getName().equals(interceptor.getClass().getName())) {
                    try {
                        interceptor.onPreMarshalRequest(context, proxy, method, args);
                        result = method.invoke(context, args);
                        interceptor.onPostMarshalRequest(context, proxy, method, args);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        return result;

    }

}
