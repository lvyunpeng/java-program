package com.lyp.sample;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 10:09
 **/
public class TestMatch {

    public static void main(String[] args) {
//        SecureRandom random = new SecureRandom();
//        System.out.println(random.generateSeed(60).toString());
//        System.out.println("Ly1d5gKjKqWR90KV7IeyUcPfTaWszptc".toCharArray().length);

        Random random = new Random();
        for(int i=0; i<100; i++){
            System.out.println(random.nextInt(2));
        }

//        String aa = "1   22";
//        System.out.println(checkNickName(aa));
    }


    public static boolean checkNickName(String nickName) {
        if (nickName == null || nickName.equals("")) {
            return false;
        }
        if(nickName.length() < 2 || nickName.length() > 20){
            return false;
        }
        if(containsEmoji(nickName)){
            return false;
        }
        if(nickName.contains(" ")){
            return false;
        }

        return true;
    }

    public static boolean containsEmoji(String source) {
        if ("".equals(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
