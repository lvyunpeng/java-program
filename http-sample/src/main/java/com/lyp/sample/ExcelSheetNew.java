package com.lyp.sample;

import com.sargeraswang.util.ExcelUtil.ExcelSheet;

import java.util.Collection;
import java.util.Map;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-04-23 20:37
 **/
public class ExcelSheetNew<T> extends ExcelSheet<T> {
    private String sheetName;
    private Map<String,String> headers;
    private Collection<T> dataset;

    public ExcelSheetNew (String sheetName, Map<String, String> headers, Collection<T> dataset){
        this.sheetName = sheetName;
        this.headers = headers;
        this.dataset = dataset;
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Collection<T> getDataset() {
        return dataset;
    }

    @Override
    public void setDataset(Collection<T> dataset) {
        this.dataset = dataset;
    }
}
