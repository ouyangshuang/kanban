package com.lianjia.sh.kanban.login;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyang
 * @since 2015-12-16 10:46
 */
@Configuration
@ConfigurationProperties(prefix = "login.interceptor")
public class LoginInterceptorProperties {

    private List<String> includePatterns = new ArrayList<>();

    private List<String> excludePatterns = new ArrayList<>();

    public List<String> getIncludePatterns() {
        return includePatterns;
    }

    public void setIncludePatterns(List<String> includePatterns) {
        this.includePatterns = includePatterns;
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }
}
