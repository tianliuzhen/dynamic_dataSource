package com.aaa.dynamic_data_mybatis.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: swagger配置
 * @Auther: wjx
 * @Date: 2019/3/1 10:14
 */
@Configuration
//开启swagger2
@EnableSwagger2
//开启swagger-bootstrap-ui的增强文档
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    /**
     * 默认扫描包
     */
    private static final String SWAGGER_SCAN_BASE_PACKAGE="com.aaa";
    private static final String SWAGGER_VERSION="1.0.0";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描包路径
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger测试标题")
                .description("Swagger测试描述")
                .license("")
                .licenseUrl("http://")
                .termsOfServiceUrl("")
                .version(SWAGGER_VERSION)
                .contact(new Contact("aaa","",""))
                .build();
    }
}
