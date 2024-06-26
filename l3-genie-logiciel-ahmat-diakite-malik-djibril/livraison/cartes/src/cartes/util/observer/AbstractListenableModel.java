package cartes.util.observer;

import java.util.*;

public abstract class AbstractListenableModel implements ListenableModel {
    private List<ModelListener> listeners;

    public AbstractListenableModel(){
        listeners = new ArrayList<>();
    }

    @Override
    public void addModelListener(ModelListener l){
        listeners.add(l);
    }

    @Override
    public void removeModelListener(ModelListener l){
        listeners.remove(l);
    }

    protected void fireChange(){
        for(ModelListener l : listeners){
            l.somethinHasChanged(this);
        }
    }
}
