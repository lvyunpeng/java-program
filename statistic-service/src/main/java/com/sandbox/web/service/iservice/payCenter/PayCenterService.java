package com.sandbox.web.service.iservice.payCenter;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sandbox.utils.common.ProductInfo;
import com.sandbox.utils.common.PurchaseRequest;
import com.sandbox.utils.common.ResponseHelper;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.utils.common.pay.ShortOrderInfo;
import com.sandbox.utils.error.HttpCode;
import com.sandbox.utils.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lv.yp
 * @Date 2017-12-13
 */
@Service
public class PayCenterService {

    @Autowired
    private PayCenterClient payCenterClient;

    @Value("${app.config.internal.api.secret}")
    public String secret;

    private static final Logger logger = LoggerFactory.getLogger(PayCenterService.class);

    @HystrixCommand(fallbackMethod = "createOrderFallback")
    public SimpleResponse<PurchaseRequest> createOrder(Long userId, String productId, String platform) {
        long timestamp = System.currentTimeMillis();
        int nonce = new Random().nextInt(1000000);
        String signature = AuthUtils.generateSignature(secret, timestamp, nonce);

        return payCenterClient.createOrder(timestamp, nonce, signature, userId, productId, platform);
    }

    public SimpleResponse<PurchaseRequest> createOrderFallback(Long userId, String productId, String platform, Throwable r){
        logger.error("createOrderFallback time out, userId:{}, productId:{}, platform:{}", userId, productId, platform, r);
        SimpleResponse response = new SimpleResponse();
        response.setCode(HttpCode.TIME_OUT);
        return response;
    }

    @HystrixCommand(fallbackMethod = "createOrderNewFallback")
    public SimpleResponse<PurchaseRequest> createOrderNew(Long userId, String productId, String currency, String pmId) {
        long timestamp = System.currentTimeMillis();
        int nonce = new Random().nextInt(1000000);
        String signature = AuthUtils.generateSignature(secret, timestamp, nonce);

        return payCenterClient.createOrderNew(timestamp, nonce, signature, userId, productId, currency, pmId);
    }

    public SimpleResponse<PurchaseRequest> createOrderNewFallback(Long userId, String productId, String currency, String pmId, Throwable r){
        logger.error("createOrderNewFallback time out, userId:{}, productId:{}, currency:{}, pmId:{}", userId, productId, currency, pmId, r);
        SimpleResponse response = new SimpleResponse();
        response.setCode(HttpCode.TIME_OUT);
        return response;
    }


    @HystrixCommand(fallbackMethod = "findShortProductInfoFallback")
    public ShortOrderInfo findShortProductInfo(String orderId) {

        SimpleResponse<ShortOrderInfo> response = payCenterClient.findShortOrderInfo(orderId);
        if(response.responseOk()){
            return response.getData();
        }
        return null;
    }

    public ShortOrderInfo findShortProductInfoFallback(String orderId, Throwable r){
        logger.error("findShortProductInfoFallback time out, orderId:{}", orderId, r);
        return null;
    }

    @HystrixCommand(fallbackMethod = "checkoutSignatureFallback")
    public boolean checkoutSignature(Long userId, String signature) {
        SimpleResponse response = payCenterClient.payssionSignatureCheck(userId, signature);
        if(response.responseOk()){
            return true;
        }
        return false;
    }

    public boolean checkoutSignatureFallback(Long userId, String signature, Throwable r){
        logger.error("checkoutSignatureFallback, userId:{}, signature:{}", userId,signature, r);
        return false;
    }

    @HystrixCommand(fallbackMethod = "getSalesProductFallback")
    public SimpleResponse<List<ProductInfo>> getSalesProduct(String type, String platform){
        long timestamp = System.currentTimeMillis();
        int nonce = new Random().nextInt(1000000);
        String signature = AuthUtils.generateSignature(secret, timestamp, nonce);
        return payCenterClient.getSalesProduct(type, platform, timestamp, nonce, signature);
    }

    public SimpleResponse<List<ProductInfo>> getSalesProductFallback(String type, String platform, Throwable r){
        logger.error("getSalesProductFallback, type:{}, platform:{}", type, platform, r);
        return ResponseHelper.createOkResponse(new ArrayList<>());
    }

}
