package uk.ac.le.cs.CO3015.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ComputerProjectMvcConfiguration extends WebMvcConfigurerAdapter{
	
	 @Override
	 public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	 configurer.enable();
	 }
	 @Bean
	 public InternalResourceViewResolver viewResolver() {
	 InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	 resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
	 resolver.setPrefix("/WEB-INF/views/");
	 resolver.setSuffix(".jsp");
	 return resolver;
	 }
}
