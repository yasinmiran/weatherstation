package observer;

public interface Observable<State> {

    void register(Observer<State> observer);

    void unregister(Observer<State> observer);

    void notifyObservers();

}
