package com.iplusplus.custopoly.model.gamemodel.Observer;

import java.io.Serializable;

/**
 * Created by Jorge on 29/04/2015.
 */
public interface GameObservable{

    public abstract void addObserver(GameObserver o);
    public abstract void deleteObserver(GameObserver o);
}
