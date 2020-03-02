package com.skillbox.sw.util;

public class ObjectsUtils {

    public static <V extends Object> V getObjectOrDefaultIfNull(V object, V defaultValue) {
        return object != null ? object : defaultValue;
    }
}
