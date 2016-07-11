package com.lianjia.sh.kanban.tools.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具 抽象类 主要是数据库表结构查询
 *
 * @author ouyang
 * @since 2015-02-12 15:25
 */
@SuppressWarnings("HardcodedLineSeparator")
public class CodeGenerate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerate.class);

    //数据库有已废弃标示
    protected static final String 已废弃 = "已废弃";
    //换行字符 忽略检查 使用系统属性
    @SuppressWarnings("AccessOfSystemProperties")
    protected static final String LINE = System.getProperty("line.separator");
    //jdbc类型转换为java的类型
    private final Map<String, String> jdbcToJavaMap = new HashMap<>();
    //退格字符
    protected static final String TAB = "\t";
    //数据库连接
    protected final Connection connection;

    /**
     * 构造器
     *
     * @param connection connection
     * @author ouyang
     * @since 2015-02-12 17:37
     */
    public CodeGenerate(Connection connection) {
        this.connection = connection;
    }

    /**
     * 支持sqlserver数据源 其他需要覆盖
     * <p/>
     * SELECT   ( CASE WHEN a.colorder = 1 THEN d.name ELSE '' END ) N'表名',
     * a.colorder N'字段序号',
     * a.name N'字段名',
     * ( CASE WHEN COLUMNPROPERTY(a.id, a.name, 'IsIdentity') = 1 THEN '√' ELSE '' END ) N'标识',
     * ( CASE WHEN ( SELECT COUNT(*) FROM sysobjects WHERE ( name IN ( SELECT name FROM sysindexes WHERE ( id = a.id ) AND ( indid IN ( SELECT indid FROM sysindexkeys WHERE ( id = a.id ) AND ( colid IN ( SELECT colid FROM syscolumns WHERE ( id = a.id ) AND ( name = a.name ) ) ) ) ) ) ) AND ( xtype = 'PK' ) ) > 0 THEN '√' ELSE '' END ) N'主键',
     * b.name N'类型',
     * a.length N'占用字节数',
     * COLUMNPROPERTY(a.id, a.name, 'PRECISION') AS N'长度',
     * ISNULL(COLUMNPROPERTY(a.id, a.name, 'Scale'), 0) AS N'小数位数',
     * ( CASE WHEN a.isnullable = 1 THEN '√' ELSE '' END ) N'允许空',
     * ISNULL(e.text, '') N'默认值',
     * ISNULL(g."value", '') AS N'字段说明'
     * FROM     sys.syscolumns a
     * LEFT JOIN sys.systypes b ON a.xtype = b.xusertype
     * INNER JOIN sys.sysobjects d ON a.id = d.id AND d.xtype  in ('U','V') AND d.name <> 'dtproperties'
     * LEFT JOIN sys.syscomments e ON a.cdefault = e.id
     * LEFT JOIN sys.extended_properties g ON a.id = g.major_id AND a.colid = g.minor_id
     * WHERE    a.id = OBJECT_ID('property')
     * ORDER BY OBJECT_NAME(a.id), a.colorder
     *
     * @param tableName 表名
     * @return 表结构列信息
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2014/12/15 12:33
     */
    protected List<Column> getColumnList(String tableName) throws SQLException {
        List<Column> columnList = new ArrayList<>();
        String sql = "SELECT TABLE_SCHEMA AS tableName, COLUMN_NAME AS columnName, COLUMN_COMMENT AS columnComment, DATA_TYPE AS dataType FROM information_schema.COLUMNS WHERE TABLE_NAME = ? AND TABLE_SCHEMA = 'kanban'";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tableName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Column column = new Column();
                    //列名格式化
                    column.setColumnName(format(rs.getString("columnName")));
                    column.setDataType(rs.getString("dataType"));
                    //注释去除换行
                    column.setColumnComment(rs.getString("columnComment").replaceAll("\\r", "").replaceAll("\\n", ""));
                    column.setDeprecated(column.getColumnComment().contains(已废弃));
                    column.setJavaType(jdbcToJavaMap.get(column.getDataType().toLowerCase()));
                    columnList.add(column);
                    System.out.println(column.getColumnComment());
                }
            }
        }
        return columnList;
    }

    /**
     * 查询表的注释
     *
     * @param tableName 表名
     * @return 表的注释
     * @throws SQLException 执行sql异常
     * @author ouyang
     * @since 2014/12/9 16:57
     */
    protected String getTableComment(String tableName) throws SQLException {
        String sql = "SELECT * FROM information_schema.Tables WHERE TABLE_NAME = ? AND TABLE_SCHEMA = 'kanban'";

        String tableComment = "";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tableName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tableComment = rs.getString("TABLE_COMMENT").replaceAll("\\r", "").replaceAll("\\n", "");
                }
            }
        }
        return tableComment;
    }

    /**
     * sqlserver 数据类型映射
     * 初始化数据库类型对应java 类型
     *
     * @author ouyang
     * @since 2014/12/22 15:55
     */
    protected void initJdbcToJavaMap() {
        jdbcToJavaMap.put("nvarchar", "String");
        jdbcToJavaMap.put("varchar", "String");
        jdbcToJavaMap.put("text", "String");
        jdbcToJavaMap.put("ntext", "String");
        jdbcToJavaMap.put("char", "String");
        jdbcToJavaMap.put("nchar", "String");

        jdbcToJavaMap.put("integer", "Integer");
        jdbcToJavaMap.put("int", "Integer");
        jdbcToJavaMap.put("tinyint", "Integer");
        jdbcToJavaMap.put("bigint", "Long");
        jdbcToJavaMap.put("bit", "Integer");
        jdbcToJavaMap.put("smallint", "Integer");

        jdbcToJavaMap.put("float", "Float");
        jdbcToJavaMap.put("decimal", "Double");
        jdbcToJavaMap.put("money", "Double");
        jdbcToJavaMap.put("smallmoney", "Double");
        jdbcToJavaMap.put("numeric", "Double");

        jdbcToJavaMap.put("timestamp", "Date");
        jdbcToJavaMap.put("time", "Date");
        jdbcToJavaMap.put("datetime2", "Date");
        jdbcToJavaMap.put("datetime", "Date");
        jdbcToJavaMap.put("smalldatetime", "Date");
        jdbcToJavaMap.put("date", "Date");

        jdbcToJavaMap.put("timestamp_import", "import java.util.Date");
        jdbcToJavaMap.put("datetime_import", "import java.util.Date");

        /**
         * 下面这些数据类型暂时不处理 如果需要自己重载
         * binary
         * datetimeoffset
         * geography
         * geometry
         * hierarchyid
         * image
         * numeric
         * real
         * sql_variant
         * sysname
         * uniqueidentifier
         * varbinary
         * xml
         */
    }

    /**
     * 格式化字段名称 表名称
     * 如果全部都为大写字符 全部转为小写
     * 首字母转为小写
     *
     * @param name 名称
     * @return 格式化后的字段或者表名称
     * @author ouyang
     * @since 2014/12/9 17:04
     */
    protected String format(String name) {
        //如果全部都为大写字符 全部转为小写
        int upperCaseNum = 0;
        for (int k = 0; k < name.length(); k++) {
            if (Character.isUpperCase(k)) {
                upperCaseNum++;
            }
        }
        if (upperCaseNum == name.length()) {
            name = name.toLowerCase();
        }
        //首字母转为小写
        name = name.substring(0, 1).toLowerCase() + name.substring(1);

        name = name.replaceAll("ID","Id");
        name = name.replaceAll("NO","No");

        return name;
    }

    /**
     * 首字母大写
     *
     * @param str 字符
     * @return 首字母大写
     * @author ouyang
     * @since 2015-02-12 17:38
     */
    protected String firstLetterUpperCase(String str) {
        if (str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        LOGGER.error("没有首字母");
        return "";
    }

}
