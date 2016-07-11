package com.lianjia.sh.kanban.config;/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.MimeType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Thymeleaf.
 *
 * @author Dave Syer
 * @author Andy Wilkinson
 * @author Stephane Nicoll
 * @author Brian Clozel
 */
@Configuration
@EnableConfigurationProperties(ThymeleafProperties.class)
@ConditionalOnClass(SpringTemplateEngine.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ThymeleafAutoConfiguration {

    private static final Log logger = LogFactory.getLog(ThymeleafAutoConfiguration.class);

    @Configuration
    @ConditionalOnMissingBean(name = "defaultTemplateResolver")
    public static class DefaultTemplateResolverConfiguration {

        @Autowired
        private ThymeleafProperties properties;

        @Autowired
        private ApplicationContext applicationContext;

        @PostConstruct
        public void checkTemplateLocationExists() {
            boolean checkTemplateLocation = this.properties.isCheckTemplateLocation();
            if (checkTemplateLocation) {
                TemplateLocation location = new TemplateLocation(
                        this.properties.getPrefix());
                if (!location.exists(this.applicationContext)) {
                    logger.warn("Cannot find template location: " + location
                                + " (please add some templates or check "
                                + "your Thymeleaf configuration)");
                }
            }
        }

//        @Bean
//        public TemplateResolver defaultTemplateResolver() {
//            TemplateResolver resolver = new TemplateResolver();
//            resolver.setResourceResolver(thymeleafResourceResolver());
//            resolver.setPrefix(this.properties.getPrefix());
//            resolver.setSuffix(this.properties.getSuffix());
//            resolver.setTemplateMode(this.properties.getMode());
//            if (this.properties.getEncoding() != null) {
//                resolver.setCharacterEncoding(this.properties.getEncoding().name());
//            }
//            resolver.setCacheable(this.properties.isCache());
//            Integer order = this.properties.getTemplateResolverOrder();
//            if (order != null) {
//                resolver.setOrder(order);
//            }
//            return resolver;
//        }

        public void setApplicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        @Bean
        public ViewResolver defaultTemplateResolver() {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(templateEngine());
            resolver.setCharacterEncoding("UTF-8");
            return resolver;
        }

        @Bean
        public TemplateEngine templateEngine() {
            SpringTemplateEngine engine = new SpringTemplateEngine();
            engine.setEnableSpringELCompiler(true);
            engine.setTemplateResolver(templateResolver());
            return engine;
        }

        @Bean
        public ITemplateResolver templateResolver() {
            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setApplicationContext(applicationContext);
            resolver.setPrefix(properties.getPrefix());
            resolver.setSuffix(this.properties.getSuffix());
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCacheable(this.properties.isCache());
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
            return resolver;
        }
    }

    @Configuration
    @ConditionalOnMissingBean(SpringTemplateEngine.class)
    protected static class ThymeleafDefaultConfiguration {

        @Autowired
        private final Collection<ITemplateResolver> templateResolvers = Collections
                .emptySet();

        @Autowired(required = false)
        private final Collection<IDialect> dialects = Collections.emptySet();

        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine engine = new SpringTemplateEngine();
            for (ITemplateResolver templateResolver : this.templateResolvers) {
                engine.addTemplateResolver(templateResolver);
            }
            for (IDialect dialect : this.dialects) {
                engine.addDialect(dialect);
            }
            return engine;
        }

    }

    @Configuration
    @ConditionalOnClass(name = "org.thymeleaf.spring4.dialect")
    protected static class ThymeleafWebLayoutConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public SpringStandardDialect layoutDialect() {
            return new SpringStandardDialect();
        }

    }

    @Configuration
    @ConditionalOnClass({ Servlet.class })
    @ConditionalOnWebApplication
    protected static class ThymeleafViewResolverConfiguration {

        @Autowired
        private ThymeleafProperties properties;

        @Autowired
        private SpringTemplateEngine templateEngine;

        @Bean
        @ConditionalOnMissingBean(name = "thymeleafViewResolver")
        @ConditionalOnProperty(name = "spring.thymeleaf.enabled", matchIfMissing = true)
        public ThymeleafViewResolver thymeleafViewResolver() {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(this.templateEngine);
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
            resolver.setContentType(appendCharset(this.properties.getContentType(),
                    resolver.getCharacterEncoding()));
            resolver.setExcludedViewNames(this.properties.getExcludedViewNames());
            resolver.setViewNames(this.properties.getViewNames());
            // This resolver acts as a fallback resolver (e.g. like a
            // InternalResourceViewResolver) so it needs to have low precedence
            resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
            resolver.setCache(this.properties.isCache());
            return resolver;
        }

        private String appendCharset(MimeType type, String charset) {
            if (type.getCharSet() != null) {
                return type.toString();
            }
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("charset", charset);
            parameters.putAll(type.getParameters());
            return new MimeType(type, parameters).toString();
        }

    }

    @Configuration
    @ConditionalOnWebApplication
    protected static class ThymeleafResourceHandlingConfig {

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledResourceChain
        public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
            return new ResourceUrlEncodingFilter();
        }

    }

}
