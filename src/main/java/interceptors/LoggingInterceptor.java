package interceptors;

import framework.Interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoggingInterceptor implements Interceptor {

    public List<String> logs = new ArrayList<>();

    @Override
    public void onPreMarshalRequest(
            Object context,
            Object proxy,
            Method callback,
            Object[] args
    ) {
        System.out.println("onPreMarshalRequest: " +
                callback.getName() + " " + Arrays.toString(args));
        logs.add(String.format(
                "method %s called with arguments %s",
                callback.getName(),
                Arrays.toString(args)
        ));
    }

    @Override
    public void onPostMarshalRequest(
            Object context,
            Object proxy,
            Method callback,
            Object[] args
    ) {
        // do some post-processing if required
        System.out.println("onPostMarshalRequest: " +
                callback.getName() + "\n");
    }

}
