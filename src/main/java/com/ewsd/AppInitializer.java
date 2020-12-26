package com.ewsd;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	    public void onStartup(ServletContext servletCxt) {

	        // Load Spring web application configuration
	        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
	        ac.register(RootConfig.class);
			ac.getEnvironment().addActiveProfile("dev");
	        ac.refresh();
	        
	        servletCxt.addListener(new ContextLoaderListener(ac));
	        
	        AnnotationConfigWebApplicationContext sc = new AnnotationConfigWebApplicationContext();
	        sc.register(ServletConfig.class);
	       
	        
	        // Create and register the DispatcherServlet
	       ServletRegistration.Dynamic registration = servletCxt.addServlet("servlet", new DispatcherServlet(sc));
	        registration.setLoadOnStartup(1);
	        registration.addMapping("/");
	
	    }
	
}
