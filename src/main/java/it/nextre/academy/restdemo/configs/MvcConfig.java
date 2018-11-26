package it.nextre.academy.restdemo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("it.nextre.academy.rest.demo")  //cerca tutte le classi @Component a partire dal package indicato
public class MvcConfig implements WebMvcConfigurer {

    @Value("${spring.mvc.view.prefix}")
    String prefix;
    @Value("${spring.mvc.view.suffix}")
    String suffix;

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix(prefix);
        bean.setSuffix(suffix);
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/res/**")
                .addResourceLocations("/resources/","/WEB-INF/contents/")
                .setCachePeriod(0)
                .resourceChain(false)
                .addResolver(new PathResourceResolver());
    }

}
