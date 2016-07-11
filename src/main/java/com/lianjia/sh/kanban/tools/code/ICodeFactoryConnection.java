package com.lianjia.sh.kanban.tools.code;

import java.sql.Connection;

/**
 * 数据库连接
 *
 * @author ouyang
 * @since 2015-02-11 09:59
 */
public interface ICodeFactoryConnection {
    /**
     * 获得数据库连接
     *
     * @return 连接
     * @author ouyang
     * @since 2015-02-12 16:53
     */
    public Connection getConnection();
}
