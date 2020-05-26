package com.sandbox.web.service.controller;

import com.ksyun.ks3.utils.StringUtils;
import com.sandbox.utils.common.PurchaseRequest;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.utils.common.pay.ShortOrderInfo;
import com.sandbox.utils.error.HttpCode;
import com.sandbox.utils.error.exception.RequestFailedException;
import com.sandbox.utils.util.JsonUtils;
import com.sandbox.web.service.service.PublicPayService;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lv.yp
 * @Date 2018-01-22
 */
@Controller
public class PublicPayController {

    @Autowired
    private PublicPayService publicPayService;

    private static final Logger logger = LoggerFactory.getLogger(PublicPayController.class);

    @ApiModelProperty(value = "创建订单", notes = "platform, 默认为app，表示app订单；为web时，表示web订单")
    @RequestMapping(
            value = "web/public/pay/api/v1/public/pay/signature",
            method = RequestMethod.GET
    )
    public String createOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("Access-Token") String accessToken,
            @RequestParam("productId") String productId,
            @RequestParam(value = "returnUrl", required = false) String returnUrl,
            @RequestParam(value = "platform", required = false, defaultValue = "app") String platform,
            Model model
    ){
        try{
            if(null == userId || StringUtils.isBlank(accessToken)){
                throw new RequestFailedException(HttpCode.AUTH_FAILED, "invalid accessToken");
            }
            publicPayService.checkoutAccessToken(userId, accessToken);

            SimpleResponse<PurchaseRequest> response = publicPayService.createOrder(userId, productId, platform);
            if(!response.responseOk()){
                logger.error("PublicPayController createOrder error:{}", JsonUtils.toJson(response));
                throw new RequestFailedException(response.getCode(), response.getMessage());
            }
            PurchaseRequest request = response.getData();
            model.addAttribute("api_key", request.getApiKey());
            model.addAttribute("api_sig", request.getApiSign());
            model.addAttribute("order_id", request.getOrderId());
            model.addAttribute("description", request.getDescription());
            model.addAttribute("amount", request.getAmount());
            model.addAttribute("currency", request.getCurrency());
            model.addAttribute("url", request.getUrl());
            model.addAttribute("returnUrl", returnUrl);
        }catch (RequestFailedException e){
            return "error";
        }catch(Exception e){
            logger.error("PublicPayController create order error, userId:{}, productId:{}, accessToken:{} ", userId, productId, accessToken, e);
            return "error";
        }

        return "pay";
    }

    @RequestMapping(
            value = "web/public/pay/api/v2/public/pay/signature",
            method = RequestMethod.GET
    )
    public String createOrderNew(
            @RequestParam("userId") Long userId,
            @RequestParam("Access-Token") String accessToken,
            @RequestParam("productId") String productId,
            @RequestParam("currency") String currency,
            @RequestParam("pmId") String pmId,
            Model model
    ){
        try{
            if(null == userId || StringUtils.isBlank(accessToken)){
                throw new RequestFailedException(HttpCode.AUTH_FAILED, "invalid accessToken");
            }
            publicPayService.checkoutAccessToken(userId, accessToken);

            SimpleResponse<PurchaseRequest> response = publicPayService.createOrderNew(userId, productId, currency, pmId);
            if(!response.responseOk()){
                logger.error("PublicPayController createOrderNew error:{}", JsonUtils.toJson(response));
                throw new RequestFailedException(response.getCode(), response.getMessage());
            }
            PurchaseRequest request = response.getData();
            model.addAttribute("api_key", request.getApiKey());
            model.addAttribute("api_sig", request.getApiSign());
            model.addAttribute("order_id", request.getOrderId());
            model.addAttribute("description", request.getDescription());
            model.addAttribute("amount", request.getAmount());
            model.addAttribute("currency", request.getCurrency());
            model.addAttribute("url", request.getUrl());
            model.addAttribute("pm_id", request.getPmId());
        }catch (RequestFailedException e){
            return "error";
        }catch(Exception e){
            logger.error("PublicPayController create order error, userId:{}, productId:{}, accessToken:{} ", userId, productId, accessToken, e);
            return "error";
        }

        return "payNew";
    }


    @ApiModelProperty(value = "创建订单,手机端使用", notes = "platform, 默认为app，表示app订单；为web时，表示web订单")
    @RequestMapping(
            value = "web/public/pay/api/v3/public/pay/signature",
            method = RequestMethod.GET
    )
    public String createOrderV3(
            @RequestParam("userId") Long userId,
            @RequestParam("signature") String signature,
            @RequestParam("productId") String productId,
            @RequestParam(value = "returnUrl", required = false) String returnUrl,
            @RequestParam(value = "platform", required = false, defaultValue = "app") String platform,
            Model model
    ){
        try{
            if(null == userId || StringUtils.isBlank(signature)){
                return "authFailed";
//                throw new RequestFailedException(HttpCode.PAYSSION_SIGNATURE_INVALID, "invalid signature");
            }
            if(!publicPayService.checkoutSignature(userId, signature)){
                return "authFailed";
            }

            SimpleResponse<PurchaseRequest> response = publicPayService.createOrder(userId, productId, platform);
            if(!response.responseOk()){
                logger.error("PublicPayController createOrderV3 error:{}", JsonUtils.toJson(response));
                throw new RequestFailedException(response.getCode(), response.getMessage());
            }
            PurchaseRequest request = response.getData();
            model.addAttribute("api_key", request.getApiKey());
            model.addAttribute("api_sig", request.getApiSign());
            model.addAttribute("order_id", request.getOrderId());
            model.addAttribute("description", request.getDescription());
            model.addAttribute("amount", request.getAmount());
            model.addAttribute("currency", request.getCurrency());
            model.addAttribute("url", request.getUrl());
            model.addAttribute("returnUrl", returnUrl);
        }catch (RequestFailedException e){
            return "error";
        }catch(Exception e){
            logger.error("PublicPayController createOrderV3 error, userId:{}, productId:{}, signature:{} ", userId, productId, signature, e);
            return "error";
        }

        return "pay";
    }


    @RequestMapping(
            value = "web/public/pay/api/v1/public/pay/return",
            method = RequestMethod.GET
    )
    public String returnUrl(
            HttpServletRequest request,
            Model model
    ){
         String transaction_id = request.getParameter("transaction_id");
         String state = request.getParameter("state");
         String order_id = request.getParameter("order_id");
         String productId = "";
         String pmId = "";
         try{
             ShortOrderInfo orderInfo = publicPayService.getShortOrderInfo(order_id);
             if(null != orderInfo){
                 productId = orderInfo.getProductId();
                 pmId = orderInfo.getPmId();
             }

         }catch(Exception e){
             logger.error("PublicPayController getProductIdByOrderId error, orderId:{}", order_id, e);
         }
         model.addAttribute("productId", productId);
         model.addAttribute("order_id", order_id);
         model.addAttribute("state", state);
         model.addAttribute("transaction_id", transaction_id);
         model.addAttribute("pmId", pmId);

         return "success";
    }

}
