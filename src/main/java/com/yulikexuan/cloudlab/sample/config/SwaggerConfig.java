//: com.yulikexuan.cloudlab.sample.config.SwaggerConfig.java


package com.yulikexuan.cloudlab.sample.config;


import com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

        // extends WebMvcConfigurationSupport { // Only used for non-spring-boot projects

    @Bean
    public Docket apiI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        CustomerController.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(this.metaData());
    }

    private ApiInfo metaData() {

        Contact contact = new Contact("Yu Li",
                "https://www.linkedin.com/in/yu-li-93a76a14/",
                "likexuan1996@yahoo.com");

        return new ApiInfo("Cloud Sample Project",
                "Cloud native by Spring-Boot REST",
                "1.0.0",
                "Terms of Service:L blah blah blah",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());

    }

//    Only used for non-spring-boot projects

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

}///:~