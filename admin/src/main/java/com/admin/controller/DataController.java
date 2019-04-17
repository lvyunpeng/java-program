package com.admin.controller;

import com.admin.data.TestData;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        TestData data = new TestData();

        List<String> xList = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0; i<100; i++){
            now.add(Calendar.DAY_OF_YEAR, -1);
            xList.add(format.format(now.getTime()));
        }
        xList.stream().sorted();
        data.setxList(xList);
        List<Integer> yList = new ArrayList<>();
        for(int i=0; i<20; i++){
            yList.add(12);
            yList.add(10);
            yList.add(16);
            yList.add(11);
            yList.add(19);
        }
        data.setyList(yList);

        return JSON.toJSONString(data);
//        return "";
    }

}
