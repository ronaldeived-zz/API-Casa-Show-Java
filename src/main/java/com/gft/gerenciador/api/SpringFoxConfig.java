package com.gft.gerenciador.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{

	@Bean
	public Docket apiDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gft.gerenciador"))
				.paths(PathSelectors.any())
//				.paths(PathSelectors.ant("/livros/**"))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Casas", "Gerencia Casas de show"))
				.tags(new Tag("Eventos", "Gerencia Eventos"))
				.tags(new Tag("Usuarios", "Gerencia os Usuarios"))
				.tags(new Tag("Vendas", "Gerencia as Vendas"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API de gerenciador de eventos")
				.description("API aberta para gerenciamento de eventos.")
				.version("1")
				.contact(new Contact("GFT", "www.exemplo.com", "contato@exemplo.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
		
	}
}
