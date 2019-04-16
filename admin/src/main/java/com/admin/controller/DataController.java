package com.admin.controller;

import com.admin.data.TestData;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        TestData data = new TestData();

        List<String> xList = new ArrayList<>();
        xList.add("测试1");
        xList.add("测试2");
        xList.add("测试3");
        xList.add("测试4");
        xList.add("测试5");
        data.setxList(xList);
        List<Integer> yList = new ArrayList<>();
        yList.add(12);
        yList.add(10);
        yList.add(16);
        yList.add(11);
        yList.add(19);
        data.setyList(yList);

        return JSON.toJSONString(data);
//        return "";
    }

}
