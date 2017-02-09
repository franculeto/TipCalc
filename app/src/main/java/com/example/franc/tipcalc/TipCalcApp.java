package com.example.franc.tipcalc;

import android.app.Application;

/**
 * Created by franc on 9/2/2017.
 */

public class TipCalcApp extends Application {

    private final static String ABOUT_URL = "https://about.me/adriancatalan";

    public static String getAboutUrl()
    {
        return ABOUT_URL;
    }

}
