package com.lianjia.sh.kanban.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author ouyang
 * @since 2015-11-15 15:21
 */
@Configuration
public class LoginWebMvc extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptorProperties loginInterceptorProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> includePatterns = loginInterceptorProperties.getIncludePatterns();

        List<String> excludePatterns = loginInterceptorProperties.getExcludePatterns();

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor());

        if (includePatterns.size() == 0) {
            includePatterns.add("/**");
        }

        interceptorRegistration.addPathPatterns(includePatterns.toArray(new String[includePatterns.size()]));
        if (excludePatterns.size() > 0) {
            interceptorRegistration.excludePathPatterns(excludePatterns.toArray(new String[excludePatterns.size()]));
        }
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

}
