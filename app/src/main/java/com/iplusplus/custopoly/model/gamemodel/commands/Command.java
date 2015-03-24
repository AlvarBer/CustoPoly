package com.iplusplus.custopoly.model.gamemodel.commands;

/**
 * Interface to implement the command pattern.
 *
 * Be liberal when creating new Commands, the provided are far from all needed.
 */
public interface Command {

    /**
     * Method to execute the command.
     */
    public void execute();
}
