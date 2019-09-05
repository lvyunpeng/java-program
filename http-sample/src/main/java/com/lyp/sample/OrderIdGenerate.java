package com.lyp.sample;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author lv.yp
 * @Date 2017-12-21
 */
public class OrderIdGenerate {

    private String currentTimeFlag;

    private String machineCode;

    private Set<String> randomSet = new HashSet<String>();

    private String order_id_regex = "%s%s%s%s";

    /**
     * 用户缓存机器标识
     */
    private String CACHE_MACHINE_FLAG = "cache_machine_flag_%s";

    /**
     * 缓存所有机器标识，set
     */
    private String CACHE_MACHINE_FLAG_ALL = "cache_machine_flag_all";

    char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * 订单id生成方式
     * 1、机器标识 2位
     * 2、类型（如谷歌充值，购买道具） 1位
     * 3、时间戳  10位
     * 4、随机数  5位
     * @return
     */
    public String getOrderId(int orderType){
        synchronized (this){
            String machineFlag = null;
            String currentTime = String.valueOf(System.currentTimeMillis());
            String timeFlag = currentTime.toString().substring(0, currentTime.length() - 3);
            String randomFlag;
            if(null != currentTimeFlag || !timeFlag.equals(currentTimeFlag)){
                randomSet.clear();
                randomFlag = getRandomChar(5);
            }else{
                do{
                    randomFlag = getRandomChar(5);
                }while(randomSet.contains(randomFlag));
            }
            randomSet.add(randomFlag);
            return String.format(order_id_regex, machineFlag, orderType, timeFlag, randomFlag);
        }

    }

    /**
     * 获得0-9,a-z,A-Z范围的随机数
     * @param length 随机数长度
     * @return String
     */
    private String getRandomChar(int length) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }
        return buffer.toString();
    }


    public static  void main(String args[]){
        OrderIdGenerate orderIdGenerate = new OrderIdGenerate();
        System.out.println(orderIdGenerate.getRandomChar(40));
    }

    /**
     * 获取机器标识
     * @return
     */



}
