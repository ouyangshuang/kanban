package com.lianjia.sh.kanban.tools;

import com.lianjia.sh.kanban.tools.code.ICodeFactoryConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * demo 142 库 实现 ICodeFactoryConnection
 *
 * @author ouyang
 * @since 2015-02-09 11:08
 */
@SuppressWarnings({"CallToDriverManagerGetConnection", "resource", "JDBCResourceOpenedButNotSafelyClosed"})
public class DemoCodeFactoryConnection implements ICodeFactoryConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoCodeFactoryConnection.class);

    @Override
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbcString = "jdbc:mysql://localhost:3306/kanban?useUnicode=true&characterEncoding=UTF8";
            connection = DriverManager.getConnection(jdbcString, "root", "ouyang");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getClass().getName(),e);
        }
        return connection;
    }

}
