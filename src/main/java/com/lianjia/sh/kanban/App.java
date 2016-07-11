package com.lianjia.sh.kanban;

import com.lianjia.sh.memcache.EnableMemcacheAnnotations;
import com.lianjia.sh.memcache.EnableMemcachedClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

/**
 *
 * kanban app
 * @author ouyang
 * @since 2016-07-05 19:30:14
 * @version v1
 * @summary kanban
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ThymeleafAutoConfiguration.class})
@EnableMemcachedClient
@EnableMemcacheAnnotations(beanName = "commonMemcachedClient")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }


}
