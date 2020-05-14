package com.classicspin.wg.manedger;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesManagerImpl implements PreferencesManager {

    private static final String PREFERENCES = "preferences";
    private static final String URL = "URL";
    private static final String USER_MODEL = "USER_MODEL";

    private SharedPreferences preferences;

    public PreferencesManagerImpl(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public String getURL() {
        return preferences.getString(URL, null);
    }

    @Override
    public void setURL(String URL) {
        preferences.edit().putString(PreferencesManagerImpl.URL, URL).apply();
    }


}
