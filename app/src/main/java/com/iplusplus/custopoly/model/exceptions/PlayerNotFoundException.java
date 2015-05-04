package com.iplusplus.custopoly.model.exceptions;

import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.app.R;

import java.io.Serializable;

public class PlayerNotFoundException extends RuntimeException implements Serializable {

    public PlayerNotFoundException() {
        super(Custopoly.getAppContext().getString(R.string.exceptions_playernotfound));
    }

    public PlayerNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public PlayerNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
