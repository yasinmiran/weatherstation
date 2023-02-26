package weather.observer;

public interface Observer<State> {

    void update(State state);

}
