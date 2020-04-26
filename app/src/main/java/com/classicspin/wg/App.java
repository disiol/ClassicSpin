package com.classicspin.wg;

import android.app.Application;

import com.classicspin.wg.manedger.PreferencesManagerImpl;
import com.onesignal.OneSignal;


public class App extends Application {

    public static  final  String a = "https://";

    @Override
    public void onCreate() {
        super.onCreate();
        // OneSignal Initialization

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

         new PreferencesManagerImpl(this);


    }
}