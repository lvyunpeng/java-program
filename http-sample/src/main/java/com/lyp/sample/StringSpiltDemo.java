package com.lyp.sample;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-26 20:00
 **/
public class StringSpiltDemo {

    public static void main(String[] args){
//        String aa = "selectable_action.45";
//        int a = aa.indexOf(".");
//        System.out.println(aa.substring(0, a));
//        System.out.println(aa.substring(a+1));
//        System.out.println(aa.substring(aa.indexOf(".")+1));
//        StringBuilder builder = new StringBuilder();
//        builder.append("12212").append("-");
//        builder.append("23232").append("-");
//        System.out.println(builder.toString());
//        System.out.println(builder.deleteCharAt(builder.lastIndexOf("-")));

        StringBuilder builder = new StringBuilder();
        String key = null;
        String resourceId = "animation_idle.1";
        if (null != resourceId) {
            try {
                if (null != key) {
                    builder.append(resourceId.substring(resourceId.indexOf(".") + 1)).append("-");
                }
                key = resourceId.substring(0, resourceId.indexOf("."));
            } catch (Exception e) {
            }
        }
        builder.deleteCharAt(builder.lastIndexOf("-"));
        System.out.println(key);
    }
}
