package com.iplusplus.custopoly.main.model.gamemodel;

/**
 * This class will have to implement some sort of Collection,
 * The idea is that it has to have an iterator.
 */
public interface AssetList {

    //NOTE: These methods may have to be changed for their
    //      equivalents in from the implemented interface.

    /**
     * Adds an asset to the list.
     *
     * @param a
     *          The asset to ad to the list.
     */
    abstract public void addAsset(Asset a);

    /**
     * Removes an asset from the list.
     *
     * @param a
     *          The asset to remove from the list.
     */

    abstract public void removeAsset(Asset a);

}
