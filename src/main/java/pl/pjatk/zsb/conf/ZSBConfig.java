package pl.pjatk.zsb.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class ZSBConfig{

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(createApiInfo());
    }
    private ApiInfo createApiInfo() {
        return new ApiInfo(
                "ZSB API",
                "Library DB",
                "1.00",
                "TOS",
                new Contact("Mikołaj", "default url", "s22982@pjwstk.edu.pl"),
                "my own licence",
                "default url",
                Collections.emptyList()
        );
    }
}
