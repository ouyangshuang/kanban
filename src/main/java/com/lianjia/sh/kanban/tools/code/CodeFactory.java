package com.lianjia.sh.kanban.tools.code;

import com.lianjia.sh.kanban.tools.code.util.ClassLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * @author ouyang
 * @since 2015-02-11 09:55
 */
public class CodeFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeFactory.class);

    /**
     * 生成javabean
     *
     * @param codeFactoryConnection 连接器实现
     * @param tableName             表名
     * @param packageStr            生成的javabean 包路径
     * @param codeGenerateAuthor    生成作者
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2015-02-12 16:48
     */
    public static void generateJavaBean(ICodeFactoryConnection codeFactoryConnection, String tableName, String packageStr, String codeGenerateAuthor) throws SQLException {
        Connection connection = codeFactoryConnection.getConnection();
        generateJavaBean(connection, tableName, packageStr, codeGenerateAuthor);

    }

    /**
     * 生成javabean
     *
     * @param codeFactoryConnectionClazz 连接器类
     * @param tableName                  表名
     * @param packageStr                 生成的javabean 包路径
     * @param codeGenerateAuthor         生成作者
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws IllegalAccessException IllegalAccessException
     * @throws InstantiationException InstantiationException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @author ouyang
     * @since 2015-02-12 16:50
     */
    public static void generateJavaBean(Class<? extends ICodeFactoryConnection> codeFactoryConnectionClazz, String tableName, String packageStr, String codeGenerateAuthor) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //实例类初始化
        Class clazz = ClassLoaderUtil.loadClass(codeFactoryConnectionClazz.getName(), CodeFactory.class);
        ICodeFactoryConnection codeFactoryConnection = (ICodeFactoryConnection) clazz.getConstructor().newInstance();
        generateJavaBean(codeFactoryConnection, tableName, packageStr, codeGenerateAuthor);
    }

    /**
     * 生成javabean
     *
     * @param connection         数据库连接
     * @param tableName          表名
     * @param packageStr         生成的javabean 包路径
     * @param codeGenerateAuthor 生成作者
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2015-02-12 16:50
     */
    public static void generateJavaBean(Connection connection, String tableName, String packageStr, String codeGenerateAuthor) throws SQLException {
        JavaBeanCodeGenerate javaBeanCodeGenerate = new JavaBeanCodeGenerate(connection, tableName, packageStr, codeGenerateAuthor);
        javaBeanCodeGenerate.generate();
    }


    /**
     * 生成数据库文档
     *
     * @param codeFactoryConnection 连接器实现
     * @param tableMap              表集合
     * @param databaseName          数据库名
     * @param generateFileDir       生成的路径
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2015-02-12 16:50
     */
    public static void generateDBDoc(ICodeFactoryConnection codeFactoryConnection, TreeMap<String, String> tableMap, String databaseName, String generateFileDir) throws SQLException {
        Connection connection = codeFactoryConnection.getConnection();
        generateDBDoc(connection, tableMap, databaseName, generateFileDir);

    }

    /**
     * 生成数据库文档
     *
     * @param codeFactoryConnectionClazz 连接器类
     * @param tableMap                   表集合
     * @param databaseName               数据库名
     * @param generateFileDir            生成的路径
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws IllegalAccessException IllegalAccessException
     * @throws InstantiationException InstantiationException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException
     * @author ouyang
     * @since 2015-02-12 17:56
     */
    public static void generateDBDoc(Class<? extends ICodeFactoryConnection> codeFactoryConnectionClazz, TreeMap<String, String> tableMap, String databaseName, String generateFileDir) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //实例类初始化
        Class clazz = ClassLoaderUtil.loadClass(codeFactoryConnectionClazz.getName(), CodeFactory.class);
        ICodeFactoryConnection codeFactoryConnection = (ICodeFactoryConnection) clazz.getConstructor().newInstance();
        generateDBDoc(codeFactoryConnection, tableMap, databaseName, generateFileDir);
    }

    /**
     * 生成数据库文档
     *
     * @param connection      数据库连接
     * @param tableMap        表集合
     * @param databaseName    数据库名
     * @param generateFileDir 生成的路径
     * @throws SQLException SQLException
     */
    public static void generateDBDoc(Connection connection, TreeMap<String, String> tableMap, String databaseName, String generateFileDir) throws SQLException {
        DBDocGenerate dbDocGenerate = new DBDocGenerate(connection, tableMap, databaseName, generateFileDir);
        dbDocGenerate.generate();
    }

}
