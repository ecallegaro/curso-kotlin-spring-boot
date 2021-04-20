package com.acme.tour.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun lojaApi() :Docket =
         Docket(DocumentationType.SWAGGER_2).
                select().
                apis(RequestHandlerSelectors.basePackage("com.acme.tour")).
                paths(PathSelectors.any()).
                build().
                apiInfo(this.metaData())


    private fun metaData() : ApiInfo =
          ApiInfoBuilder().
                title("API da Loja get Promoções Evandro LTDA").
                description("Esta API fornece recursos para consulta de promocoes de eventos e viagens").
                version("1.0.0").
                build()
}