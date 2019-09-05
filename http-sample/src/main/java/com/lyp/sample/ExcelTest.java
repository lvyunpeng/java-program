package com.lyp.sample;

import com.sargeraswang.util.ExcelUtil.ExcelSheet;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-04-22 15:49
 **/
public class ExcelTest {

    public static void main(String[] args) throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map =new LinkedHashMap<>();
        map.put("name", "");
        map.put("age", "");
        map.put("birthday","");
        map.put("sex","");
        Map<String,Object> map2 =new LinkedHashMap<String, Object>();
        map2.put("name", "测试是否是中文长度不能自动宽度.测试是否是中文长度不能自动宽度.");
        map2.put("age", null);
        map2.put("sex", null);
        map.put("birthday",null);
        Map<String,Object> map3 =new LinkedHashMap<String, Object>();
        map3.put("name", "张三");
        map3.put("age", 12);
        map3.put("sex", "男");
        map3.put("birthday",new Date());
        list.add(map);
        list.add(map2);
        list.add(map3);
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("name","姓名");
        map1.put("age","年龄");
        map1.put("birthday","出生日期");
        map1.put("sex","性别");

        Map<String, Map<String, Object>> newMap = new HashMap<>();
        newMap.put("map1", map);
        newMap.put("map2", map2);
        newMap.put("map3", map3);

        List<ExcelSheet<Map<String, Object>>> sheetList = new ArrayList<>();
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

//        // 生成一个表格
//        for(String type : new String[]{"active", "register"}){
//            ExcelSheet<Map<String, Object>> excelSheet = new ExcelSheet<>();
//            excelSheet.setDataset(newMap.values());
//            excelSheet.setHeaders(map1);
//            excelSheet.setSheetName(type);
//            sheetList.add(excelSheet);
//        }

        for(String type : new String[]{"active", "register"}){
            ExcelSheetNew<Map<String, Object>> excelSheet = new ExcelSheetNew(type, map1, list);
//            excelSheet.setDataset(newMap.values());
//            excelSheet.setHeaders(map1);
//            excelSheet.setSheetName(type);
            sheetList.add(excelSheet);
        }


        File f= new File("d:/test16.xls");
        OutputStream out = new FileOutputStream(f);
        ExcelUtil.exportExcel(sheetList, out);
        File f1= new File("d:/test17.xls");
        OutputStream out1 = new FileOutputStream(f1);
        ExcelUtil.exportExcel(map1, list, out1 );
        out.close();
    }
}
