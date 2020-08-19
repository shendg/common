package com.sdg.core.utils;


import java.util.UUID;

public class UUIDUtil {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }
}
