package interceptor;

import java.util.*;

public class InterceptorRegistry {

    private static final Map<Class<?>, List<Interceptor>> INTERCEPTORS = new HashMap<>();

    public static void register(Class<?> clazz, Interceptor interceptor) {
        if (!INTERCEPTORS.containsKey(clazz)) {
            INTERCEPTORS.put(clazz, new ArrayList<>());
        }
        INTERCEPTORS.get(clazz).add(interceptor);
    }

    public static void unregister(Class<?> clazz) {
        INTERCEPTORS.remove(clazz);
    }

    public static List<Interceptor> getInterceptors(Class<?> clazz) {
        return INTERCEPTORS.getOrDefault(clazz, Collections.emptyList());
    }

}