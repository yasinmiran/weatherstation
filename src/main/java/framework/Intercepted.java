package framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This method annotation allows the dispatcher
 * to dynamically figure out which methods need to
 * be intercepted.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Intercepted {

    /**
     * The mapping {@link Interceptor} implementation
     * for the targeted method.
     *
     * @return Class
     */
    Class<?> handler();

}
