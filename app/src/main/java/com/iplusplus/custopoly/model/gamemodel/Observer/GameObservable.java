package com.iplusplus.custopoly.model.gamemodel.Observer;

import java.io.Serializable;

/**
 * Created by Jorge on 29/04/2015.
 */
public interface GameObservable{

    public void addObserver(GameObserver o);
    public void deleteObserver(GameObserver o);
}
