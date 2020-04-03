package com.sandbox.web.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lv.yp
 * @Date 2017-11-24
 */
@RestController
public class HealthCheckController {
    @RequestMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
}
