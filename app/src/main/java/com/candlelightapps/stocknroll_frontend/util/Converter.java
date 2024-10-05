package com.candlelightapps.stocknroll_frontend.util;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

public class Converter {
    @InverseMethod("stringToInt")
    public static String intToString(int value) {
//        if (value == 0) {
//            return "";
//        }
        return String.valueOf(value);
    }

    public static int stringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}