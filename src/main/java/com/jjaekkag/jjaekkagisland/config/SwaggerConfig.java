package com.jjaekkag.jjaekkagisland.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author jeongheekim
 * @date 12/3/23
 */
@OpenAPIDefinition(
        info = @Info(title = "째깍섬 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
}
