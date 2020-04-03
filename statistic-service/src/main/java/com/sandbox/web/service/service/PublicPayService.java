package com.sandbox.web.service.service;

import com.sandbox.utils.common.PurchaseRequest;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.utils.common.pay.ShortOrderInfo;
import com.sandbox.utils.error.HttpCode;
import com.sandbox.utils.error.exception.RequestFailedException;
import com.sandbox.web.service.iservice.authCenter.AuthCenterService;
import com.sandbox.web.service.iservice.payCenter.PayCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lv.yp
 * @Date 2018-01-22
 */
@Service
public class PublicPayService {

    @Autowired
    private PayCenterService payCenterService;

    @Autowired
    private AuthCenterService authCenterService;

    public SimpleResponse<PurchaseRequest> createOrder(Long userId, String productId, String platform) {
        return payCenterService.createOrder(userId, productId, platform);
    }

    public SimpleResponse<PurchaseRequest> createOrderNew(Long userId, String productId, String currency, String pmId) {
        return payCenterService.createOrderNew(userId, productId, currency, pmId);
    }

    public void checkoutAccessToken(Long userId, String accessToken){
        if(!authCenterService.checkoutAccessToken(userId, accessToken)){
            throw new RequestFailedException(HttpCode.AUTH_FAILED, "invalid accessToken");
        }
    }

    public ShortOrderInfo getShortOrderInfo(String orderId){
        return payCenterService.findShortProductInfo(orderId);
    }

    public boolean checkoutSignature(Long userId, String signature) {
        return payCenterService.checkoutSignature(userId, signature);
    }
}
