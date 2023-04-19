package cartes.util.observer;

public interface ListenableModel {
    void addModelListener(ModelListener l);
    void removeModelListener(ModelListener l);
}
