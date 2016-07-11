package com.lianjia.sh.kanban.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ouyang
 * @since 2015-08-11 11:33
 */
@Configuration
public class TransactionManagerConfig {

    @Autowired
    private DataSource kanbanDataSource;

    /**
     * 事务管理
     *
     * @return
     * @author ouyang
     * @since 2015-08-12 10:51
     */
    @Bean(name = {"kanbanTransactionManager"})
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(kanbanDataSource);
    }

    /**
     * 配置事务传播特性
     *
     * @return
     * @author ouyang
     * @since 2015-08-12 10:54
     */
    @Bean(name = {"transactionAttributeSource"})
    public TransactionAttributeSource transactionAttributeSource() {
        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();

        Map<String, TransactionAttribute> nameMap = new HashMap<>();
        nameMap.put("update*", new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));
        nameMap.put("insert*", new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));
        nameMap.put("del*", new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED));

        DefaultTransactionAttribute other = new DefaultTransactionAttribute();
        other.setReadOnly(true);
        nameMap.put("*", other);

        nameMatchTransactionAttributeSource.setNameMap(nameMap);
        return nameMatchTransactionAttributeSource;
    }


    /**
     * 事务管理
     *
     * @return
     * @author ouyang
     * @since 2015-08-12 11:43
     */
    @Bean(name = {"transactionAttributeSourceAdvisor"})
    public Advisor transactionAdvisor() {

        //事务拦截
        //pointcut-ref
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* com.lianjia.sh.kanban.service*..update*(..)) " +
                                                " || execution(* com.lianjia.sh.kanban.service*..insert*(..)) " +
                                                " || execution(* com.lianjia.sh.kanban.service*..del*(..))");

        //advice-ref
        TransactionInterceptor transactionInterceptorAdvice = new TransactionInterceptor(txManager(),
                transactionAttributeSource());

        return new DefaultPointcutAdvisor(aspectJExpressionPointcut, transactionInterceptorAdvice);
    }

}
