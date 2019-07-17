package com.weixin.publicnation.utils;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {

    public static String getSha1(String str) {

        char chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] digest = md.digest(str.getBytes());
            StringBuilder sb=new StringBuilder();
            for (byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkSignal(String token,String timestamp,String nonce,String signature){
        String[] strs=new String[]{token,timestamp,nonce};
        Arrays.sort(strs);
        String str=strs[0]+strs[1]+strs[2];
        String sha1 = getSha1(str);
        return signature.equalsIgnoreCase(sha1);
    }
}

