package com.lyp.sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-04-25 16:24
 **/
public class YamlTest {


    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    public static void main(String[] args){
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date2 = calendar.getTime();
        System.out.println(differentDaysByMillisecond(date1, date2));


//        Random random = new Random();
//        for(int i=0; i<10; i++){
//            System.out.println( random.nextInt(2));
//        }



//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("zhangsan", 10);
//        map.put("lisi", 8);
//        map.put("wangwu", 18);
//        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return (o2.getValue() - o1.getValue());
//            }
//        });
//
//        for(Map.Entry<String, Integer> t:list){
//            System.out.println(t.getKey()+":"+t.getValue());
//        }
//        SimpleDateFormat format = new SimpleDateFormat("yyMMdd HH:mm:ss:SSS");
//        String date = format.format(new Date());
//        System.out.println(new Date().getTime());
//        System.out.println(date + ": " + date.length());
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        for(int i=0; i<10; i++){
//            System.out.println(atomicInteger.incrementAndGet());
//        }


//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -2);
//        int week = calendar.get(Calendar.WEEK_OF_YEAR);
//        int year = calendar.get(Calendar.YEAR);
//        String weekCount =  String.format("%d%d", year, week);
//        System.out.println(weekCount);
//
//        int month = calendar.get(Calendar.MONTH) + 1;
//        year = calendar.get(Calendar.YEAR);
//        String monthCount = String.format("%d%d", year, month);
//        System.out.println(monthCount);

//        System.out.println(new GroupChatPriceConfig().getPrice());
//        String yamlStr = "key: hello yaml";
//        Yaml yaml = new Yaml();
//        Object ret = yaml.load(yamlStr);
//        System.out.println(ret);
    }
}
