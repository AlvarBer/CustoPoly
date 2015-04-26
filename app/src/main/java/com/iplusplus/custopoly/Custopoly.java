package com.iplusplus.custopoly;

import android.app.Application;
import android.content.Context;
import com.iplusplus.custopoly.app.R;
import com.iplusplus.custopoly.model.ShopKeeper;
import com.iplusplus.custopoly.model.ThemeHandler;

/**
 * Created by Fran on 02/04/2015.
 *
 * Global class which contains the context of the application
 */
public class Custopoly extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        Custopoly.context = getApplicationContext();
        ThemeHandler.getInstance(); //Create singleton instance
    }

    public static Context getAppContext() {
        return Custopoly.context;
    }
}

