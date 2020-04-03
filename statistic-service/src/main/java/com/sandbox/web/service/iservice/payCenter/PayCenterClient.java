package com.sandbox.web.service.iservice.payCenter;

import com.sandbox.utils.common.PurchaseRequest;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.utils.common.pay.ShortOrderInfo;
import com.sandbox.utils.constants.ProductType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lv.yp
 * @Date 2017-12-13
 */
@FeignClient("pay-center")
public interface PayCenterClient {

    @RequestMapping(
            value = "pay/api/v1/public/pay/signature",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResponse<PurchaseRequest> createOrder(
            @RequestParam("timestamp") Long timestamp,
            @RequestParam("nonce") Integer nonce,
            @RequestParam("signature") String signature,
            @RequestParam("userId") Long userId,
            @RequestParam("productId") String productId,
            @RequestParam("platform") String platform
    );

    @RequestMapping(
            value = "pay/api/v1/public/pay/find/short/info",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResponse<ShortOrderInfo> findShortOrderInfo(
            @RequestParam("orderId") String orderId
    );

    @RequestMapping(
            value = "pay/api/v2/public/pay/signature",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResponse<PurchaseRequest> createOrderNew(
            @RequestParam("timestamp") Long timestamp,
            @RequestParam("nonce") Integer nonce,
            @RequestParam("signature") String signature,
            @RequestParam("userId") Long userId,
            @RequestParam("productId") String productId,
            @RequestParam("currency") String currency,
            @RequestParam("pmId") String pmId
    );

    @RequestMapping(
            value = "/api/v1/pay/payssion/signature",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResponse payssionSignatureCheck(
            @RequestParam("userId") Long userId,
            @RequestParam("signature") String signature
    );

    @RequestMapping(
            value = "/api/v1/pay/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )  public SimpleResponse getSalesProduct(
            @RequestParam(value = "type", required = false, defaultValue = ProductType.PRODUCT_TYPE_DIAMOND) String type,
            @RequestParam(value = "platform", required = false, defaultValue = ProductType.PRODUCT_ANDROID) String platform,
            @RequestParam("timestamp") Long timestamp,
            @RequestParam("nonce") Integer nonce,
            @RequestParam("signature") String signature
    );
}
