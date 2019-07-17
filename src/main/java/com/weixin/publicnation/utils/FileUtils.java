package com.weixin.publicnation.utils;

public class FileUtils {

    public static String castFileType(String contentType){
        if (contentType.contains("ima")){
            return "image";
        }

        return null;
    }
}
