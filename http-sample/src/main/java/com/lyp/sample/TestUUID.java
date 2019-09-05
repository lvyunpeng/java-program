package com.lyp.sample;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-10 11:17
 **/
public class TestUUID {

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static void main(String[] args){
        System.out.println("cache.user.game.data.535782432".length());
//        for(int i=0; i<50; i++){
//            String uuid = getUUID32();
//            System.out.println(uuid + ":   " + md5Password(uuid) );
//        }
    }


}
