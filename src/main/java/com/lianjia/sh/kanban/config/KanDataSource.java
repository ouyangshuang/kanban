package com.lianjia.sh.kanban.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ouyang
 * @since 2016-01-30 17:00
 */
@Configuration
public class KanDataSource {

    @Bean(name = {"kanbanDateSource"}, destroyMethod = "close")
    @ConfigurationProperties(prefix = "kanban.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


}
