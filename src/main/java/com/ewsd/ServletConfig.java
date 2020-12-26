package com.ewsd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ewsd.util.Constants;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.ewsd.controllers"})
public class ServletConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for -
		//uploded IMAGES
		registry.addResourceHandler("/uploaded_img/**").addResourceLocations("file:///"+Constants.UPLOADED_FOLDER);

// IMAGES
registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/img/");

// CSS
registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");

// JAVASCRIPT
registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");

// Plugins
registry.addResourceHandler("/vendor/**").addResourceLocations("/WEB-INF/resources/vendor/");

// Dist
registry.addResourceHandler("/scss/**").addResourceLocations("/WEB-INF/resources/scss/");

registry.addResourceHandler("/lib/**").addResourceLocations("/WEB-INF/resources/lib/");
	}
	@Bean
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}
