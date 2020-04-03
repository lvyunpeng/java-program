package com.sandbox.web.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lv.yp
 * @Date 2017-10-17
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sandbox.web.service.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("珠海沙盒网络科技在线文档--游戏相关")
                .description("公共返回参数:1, SUCCESS; 2, FAILED; 3, PARAM ERROR; 4, INNER ERROR; 5, TIME OUT; 6, AUTH FAILED, 客户端请求的URL为 http://1120.92.158.119/msg + 具体路径;")
                .termsOfServiceUrl("")
                .contact("lv.yp")
                .version("1.0")
                .build();
    }
}
