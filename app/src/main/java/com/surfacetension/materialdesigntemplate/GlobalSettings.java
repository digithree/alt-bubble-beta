package com.surfacetension.materialdesigntemplate;

import android.content.SharedPreferences;

/**
 * Created by simonkenny on 22/01/15.
 */
public class GlobalSettings {
    private final static GlobalSettings INSTANCE = new GlobalSettings();

    private static final String SHARED_PREFS_EMPTY_STRING = "[[empty]]";

    public static final String SHARED_PREFS_EXAMPLE_PREF = "EXAMPLE_PREF";

    SharedPreferences sharedPreferences;

    // thwart instantiation
    protected GlobalSettings() {
        sharedPreferences = null;
    }

    public static GlobalSettings getInstance() {
        return INSTANCE;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    // --- access

    public void setStringKey(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringKey(String key) {
        String str = sharedPreferences.getString(key, SHARED_PREFS_EMPTY_STRING);
        if( str.equals(SHARED_PREFS_EMPTY_STRING) ) {
            return null;
        }
        return str;
    }
}
