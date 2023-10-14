package com.example.QuanLyBanHang.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Website Jelly APIs document")
//                        .description("QL may bay project")
//                        .version("1.0")
//                );
        return new OpenAPI().info(new Info().title("Website Jelly APIs Document").description("Website Jelly Project")
                .version("1.0"));
    }
}
