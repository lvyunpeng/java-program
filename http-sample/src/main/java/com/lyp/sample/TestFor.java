package com.lyp.sample;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-25 18:09
 **/
public class TestFor {

    private static final String BIRTH_REG = "\\d{4}-\\d{1,2}-\\d{1,2}";


    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static final String SIGN_ALGORITHMS256 = "SHA256WithRSA";

    public static final Base64.Decoder decoder = Base64.getDecoder();

    public static boolean doCheck(String content, String sign, String publicKey, String signtype) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = decoder.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = null;
            if ("RSA256".equals(signtype)) {
                signature = java.security.Signature.getInstance(SIGN_ALGORITHMS256);
            } else {
                signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            }
            signature.initVerify(pubKey);
            signature.update(content.getBytes("utf-8"));
            boolean bverify = signature.verify(decoder.decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static String content = "accessMode=0&amount=10.00&bankId=AliPay&extReserved=11&notifyTime=1554733129506&orderId=A3b6a9dd1d*****dee66b32e085692&orderTime=2019-04-08 22:18:48&payType=4&productName=1000元宝&requestId=8328******592&result=0&spending=0.00&tradeTime=2019-04-08 22:18:49&userName=80086******332";

    static String sign  =  "Xmc+JExSPFr9FUjd4IPKtRAkkkOTm0oXCFNwlvXdQJHbhB0prD8ycpw0Wdr6enKLUgHBKB8JQ9s7VB9wMcgRXOJ6iArn0NqTTTl9Z5gP7zGBGDVH8J22CRObd8JKeQ9YiJ2SUM+r2H+jQdMpj3n3Cp47Z4rGR3kUDDIRCOEhSwHAqwzdeFJg2utEJfuYQIhseM7/hxB/H1ssoe9SaIAmKfjuZgYS+VzsUBjCiTmd9jKdpq0kTCEas1lL8MZVxkZ91g+zd2PH0ajgYkU8wvxRZgLRWw4h28DrKD7ziQKHOt92ZZjvcej+maSSDeQtyp7DhFgH4T+2uHy/c3Kf33kUFw==";

    public static String signtype  = "RSA256";

    public static final String devPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxuJTzr44cJQGXT6Y09Y902AoIDhKhJv8/QSZLnG92L8yIhfLDCCjd5Xprnqxu0GirGESywin1qj6QhHbtBxr5hXz4*****wOYk8700vRokdZq35lfg+oYcc9u2ztFRvhthHwH5R7bNCX0aiFP3POjhGPriRLzWAcA4p0FfWg1cqTDoGhH89SKRopdr1AKVpjOZxuOcnnN/vejgdGgUTurvypZCFCnUifcGxjtE/tWQmVv9xNQLwDACJm8JjiP2dAm3l4KViSl/j6j65kGT7WxHTxNM1jvLWzPMnPCvn/O9cWmsfAfmwaa6sIot1L5KlgNwIDAQAB";

    public static void main(String[] args) {

        Boolean bool =  doCheck(content, sign, devPubKey, signtype);
        if(bool) {
            System.out.println("verification is successful!");
        }
        else {
            System.out.println("verification is false!");
        }
    }


//    public static void main(String[] args){
//        String packageName = "g1008_blockymods_UA";
//        String[] arrays = packageName.split("_");
//        for(String array : arrays){
//            System.out.println(array);
//        }
////        String[] arrays = packageName.split(".");
////        int index = packageName.lastIndexOf(".");
////        System.out.println(index);
////        if(index == -1){
////            System.out.println(packageName);
////        }else{
////            System.out.println(packageName.substring(index + 1));
////        }
////        if(packageName.contains(".")){
////            packageName.substring(packageName.lastIndexOf("."));
////        }
////        packageName = arrays.length == 0 ? packageName : arrays[arrays.length - 1];
////        System.out.println(packageName);
//
////
////        Pattern pattern = Pattern.compile(BIRTH_REG);
////        String birthday = "2019-09-01";
////        Matcher matcher = pattern.matcher(birthday);
////        System.out.println(matcher.matches());
////        birthday = "2019-9-01";
////        matcher = pattern.matcher(birthday);
////        System.out.println(matcher.matches());
////        birthday = "2019-9-1";
////        matcher = pattern.matcher(birthday);
////        System.out.println(matcher.matches());
////        birthday = "2019-09-1";
////        matcher = pattern.matcher(birthday);
////        System.out.println(matcher.matches());
//
//
//
////        String a = "com.sandbox.blockymods";
////        System.out.println(a.substring(a.lastIndexOf(".") + 1));
////        List<Integer> list = new ArrayList<>();
////        for(int i=0; i<10; i++){
////            list.add(i);
////        }
////        int start = 5;
////        int end = 8;
////        for(Integer i : list){
////            if(start > i){
////                System.out.println("start: " + i);
////            }else if(end > i){
////                System.out.println("end: " + i);
////            }else{
////                break;
////            }
////            System.out.println("iiiiiiii: " + i);
////        }
//
//    }
}
