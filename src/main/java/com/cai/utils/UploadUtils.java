package com.cai.utils;

import java.util.UUID;

public class UploadUtils {

    public static String getUUIDFileName(String fileName){
        // 将文件名的前面部分进行截取：xx.jpg   --->   .jpg
        int idx = fileName.lastIndexOf(".");
        String extention = fileName.substring(idx);
        String uuidFileName = UUID.randomUUID().toString().replace("-", "")+extention;
        return uuidFileName;
    }

}

