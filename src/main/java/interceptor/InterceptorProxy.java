package interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class InterceptorProxy implements InvocationHandler {

    private final Object target;

    public InterceptorProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        List<Interceptor> interceptors = InterceptorRegistry.getInterceptors(target.getClass());

        for (Interceptor interceptor : interceptors) {
            interceptor.onPreMarshalRequest(target, method, args);
        }

        Object result = method.invoke(target, args);

        for (Interceptor interceptor : interceptors) {
            interceptor.onPostMarshalRequest(target, method, args);
        }

        return result;

    }

}