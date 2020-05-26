package com.sandbox.web.service.iservice.authCenter;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sandbox.utils.common.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author lv.yp
 * @Date 2017-12-13
 */
@Service
public class AuthCenterService {

    @Autowired
    private AuthCenterClient authCenterClient;

    @Value("${app.config.internal.api.secret}")
    public String secret;

    private static final Logger logger = LoggerFactory.getLogger(AuthCenterService.class);


    @HystrixCommand(fallbackMethod = "checkoutAccessTokenFallback")
    public boolean checkoutAccessToken(Long userId, String accessToken){

        SimpleResponse response = authCenterClient.checkoutAccessToken(userId, accessToken);
        if(response.responseOk()){
            return true;
        }
        return false;
    }

   public boolean checkoutAccessTokenFallback(Long userId, String accessToken){
       logger.error("checkoutAccessTokenFallback time out, userId:{}, accessToken:{}", userId, accessToken);
        return false;
   }
}
