package com.lianjia.sh.kanban.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author ouyang
 * @since 2015-08-11 11:21
 */
@Configuration
public class SqlSessionConfig {

	@Resource
    private DataSource userDateSource;

    @Bean
    public Properties mybatisConfigurationProperties() {
        Properties properties = new Properties();
        //全局映射器启用缓存
        properties.setProperty("cacheEnable", "false");
        //查询时，关闭关联对象即时加载以提高性能
        properties.setProperty("lazyLoadingEnabled", "true");
        //设置关联对象加载的形态，此处为按需加载字段(加载字段由SQL指定)，不会加载关联表的所有字段，以提高性能 -->
        properties.setProperty("aggressiveLazyLoading", "false");
        //对于未知的SQL查询，允许返回不同的结果集以达到通用的效果 -->
        properties.setProperty("multipleResultSetsEnabled", "true");
        //允许使用列标签代替列名
        properties.setProperty("useColumnLabel", "true");
        //允许使用自定义的主键值(比如由程序生成的UUID 32位编码作为键值)，数据表的PK生成策略将被覆盖 -->
        properties.setProperty("useGeneratedKeys", "true");
        //给予被嵌套的resultMap以字段-属性的映射支持 -->
        properties.setProperty("autoMappingBehavior", "FULL");
        //对于批量更新操作缓存SQL以提高性能
        properties.setProperty("defaultExecutorType", "SIMPLE");
        //数据库超过300秒仍未响应则超时
        properties.setProperty("defaultStatementTimeout", "300");
        return properties;
    }

    @Bean(name = {"sqlSessionFactory"})
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(userDateSource);
        sqlSessionFactoryBean.setConfigurationProperties(mybatisConfigurationProperties());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        org.springframework.core.io.Resource[] kanban = resolver.getResources("classpath:mapper/*Mapper.xml");
        List<org.springframework.core.io.Resource> list =  new ArrayList<>();
        list.addAll(Arrays.asList(kanban));
        sqlSessionFactoryBean.setMapperLocations(list.toArray(new org.springframework.core.io.Resource[list.size()]));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = {"sqlSession"})
    public SqlSession sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}
