package io.api.btgpactual.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final BuildProperties buildProperties;

    private Info ApiInfo() {
        return new Info()
                .title("Btg Pactual Challenge")
                .description("A challange made by btg pactual.")
                .version(buildProperties.getVersion());
    }

    @Bean
    public OpenAPI ApiDocs() {
        return new OpenAPI()
                .info(ApiInfo());
    }
}
