package com.sandbox.web.service.iservice.authCenter;

import com.sandbox.utils.common.SimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lv.yp
 * @Date 2017-12-13
 */
@FeignClient("auth-center")
public interface AuthCenterClient {

    @RequestMapping(
            value = "/api/v1/user/auth/checkout/access-token",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SimpleResponse checkoutAccessToken(
            @RequestParam("userId") Long userId,
            @RequestParam("AccessToken") String accessToken
    );
}
