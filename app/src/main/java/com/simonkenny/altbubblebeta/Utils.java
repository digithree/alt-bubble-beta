package com.simonkenny.altbubblebeta;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by simonkenny on 22/01/15.
 */
public class Utils {
    public static int safeParseInt(String str) {
        int result = 0;
        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

    public static float safeParseFloat(String str) {
        float result = 0.f;
        try {
            result = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            result = 0.f;
        }
        return result;
    }

    public static int getAppVersionCode(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }

    public static String getAppVersionName(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    public static void addFragment( Activity activity,
                                int containerViewId,
                               Fragment fragment,
                               String fragmentTag) {
        activity.getFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    public static void replaceFragment( Activity activity,
                                    int containerViewId,
                                   Fragment fragment,
                                   String fragmentTag,
                                   String backStackTransitionName) {
        activity.getFragmentManager()
                .beginTransaction()
                .setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN )
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackTransitionName)
                .commit();
    }
}
