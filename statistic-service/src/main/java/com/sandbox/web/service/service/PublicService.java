package com.sandbox.web.service.service;

import com.sandbox.utils.common.ProductInfo;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.web.service.iservice.authCenter.AuthCenterService;
import com.sandbox.web.service.iservice.payCenter.PayCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-15 11:57
 **/
@Service
public class PublicService {

    @Autowired
    private PayCenterService payCenterService;

    public SimpleResponse<List<ProductInfo>> getProducts(String type, String platform) {
        return payCenterService.getSalesProduct(type, platform);
    }
}
