package by.zastr.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("by.zastr")
public class SpringConfig {
    private final static String FILE_BASENAME = "errorMessage";

    @Bean
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames(FILE_BASENAME);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
