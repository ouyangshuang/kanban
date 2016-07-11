package com.lianjia.sh.kanban.framework;

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 78672 on 2014/10/13.
 */
public class MD5 {
    private static String getMD5String(byte[] bytes){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String get(String key){
        Assert.hasLength(key);
        return MD5.getMD5String(key.getBytes());
    }


}
