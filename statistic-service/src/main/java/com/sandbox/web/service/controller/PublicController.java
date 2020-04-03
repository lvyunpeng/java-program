package com.sandbox.web.service.controller;

import com.sandbox.utils.common.ProductInfo;
import com.sandbox.utils.common.ResponseHelper;
import com.sandbox.utils.common.SimpleResponse;
import com.sandbox.utils.constants.ProductType;
import com.sandbox.utils.error.HttpCode;
import com.sandbox.utils.error.exception.RequestFailedException;
import com.sandbox.utils.util.JsonUtils;
import com.sandbox.web.service.service.PublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-15 11:50
 **/
@RestController
public class PublicController {

    @Autowired
    private PublicService publicService;

    private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @RequestMapping(
            value = "web/public/pay/api/v1/public/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )  public SimpleResponse getSalesProduct(
            @RequestParam(value = "type", required = false, defaultValue = ProductType.PRODUCT_TYPE_DIAMOND) String type,
            @RequestParam(value = "platform", required = false, defaultValue = ProductType.PRODUCT_ANDROID) String platform,
            @RequestParam(value = "jsoncallback", required = false) String jsonCallback
    ){

        SimpleResponse<List<ProductInfo>> response = new SimpleResponse();
        try{
            response = publicService.getProducts(type,platform);
        }catch(RequestFailedException e){
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setCode(HttpCode.INNER_ERROR);
            response.setMessage("INNER ERROR");
            logger.error("PayController getSalesProduct error, type:{}",  type, e);
        }

        if (null != jsonCallback) {
            String callback = String.format("%s(%s)", jsonCallback, JsonUtils.toJson(response));
            return ResponseHelper.createOkResponse(callback);
        }
        return response;
    }
}
