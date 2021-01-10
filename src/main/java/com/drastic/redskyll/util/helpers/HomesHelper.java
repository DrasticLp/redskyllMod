package com.drastic.redskyll.util.helpers;

import libs.org.json.simple.JSONObject;

public class HomesHelper
{
    public static boolean isHomeExists(String string, JSONObject obj)
    {
        return obj.containsKey(string);
    }
}
