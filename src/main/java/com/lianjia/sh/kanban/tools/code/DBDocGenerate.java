package com.lianjia.sh.kanban.tools.code;

import com.lianjia.sh.kanban.tools.code.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ouyang
 * @since 2015-02-12 10:17
 */
public class DBDocGenerate extends CodeGenerate {

    //生成文件目录
    private final String generateFileDir;
    //数据库名
    private String databaseName = "datebaseDoc";
    //数据表集合
    private final TreeMap<String, String> treeMap = new TreeMap<>();
    //模板目录名称
//    @SuppressWarnings("ConstantConditions")
//    public static final String DOC_TEMPLATE_DIR = DBDocGenerate.class.getResource("dycode").getPath();
    //index模板
    private final StringBuilder indexSb = new StringBuilder();
    //表页面模板
    private final StringBuilder columnSb = new StringBuilder();


    /**
     * 构造函数
     *
     * @param connection      连接
     * @param treeMap         表集合
     * @param databaseName    数据库名
     * @param generateFileDir 生成文件目录
     */
    public DBDocGenerate(Connection connection, TreeMap<String, String> treeMap, String databaseName, String generateFileDir) {
        super(connection);
        this.treeMap.putAll(treeMap);
        this.databaseName = databaseName;
        this.generateFileDir = generateFileDir;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DBDocGenerate.class);

    /**
     * 生成db文档
     *
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2015-02-12 17:38
     */
    public void generate() throws SQLException {

        LOGGER.info("读取模板文件：/dycode/tables.html");
        FileUtil.readFile(indexSb, "/dycode/tables.html");

        LOGGER.info("读取模板文件：/dycode/tables.html");
        FileUtil.readFile(columnSb, "/dycode/table.html");

        //清除以前的生成的文件和目录 准备工作
        deleteSameFile();
        //生成数据库目录文件
        generateTableListHtml();

        for (String tableName : treeMap.keySet()) {
            generateTableHtml(tableName);
        }

    }

    /**
     * 清除以前的生成的文件和目录 重建新的目录
     *
     * @author ouyang
     * @since 2014/12/15 17:21
     */
    private void deleteSameFile() {
        File listDir = new File(generateFileDir + "/list");
        if (listDir.exists() && listDir.isDirectory()) {
            FileUtil.deleteDir(listDir);
        }
        File indexFile = new File(generateFileDir + '/' + databaseName + ".html");
        if (indexFile.exists()) {
            if (!indexFile.delete()) {
                LOGGER.error("删除文件:{}/{}.html 失败", generateFileDir, databaseName);
            }
        }

        if(listDir.mkdirs()) {
            LOGGER.info("生成目录{}", listDir.getPath());
        }
        File styleFile = new File("/dycode/style.css");
        LOGGER.info("开始复制样式文件/dycode/style.css");
        FileUtil.copyFile("/dycode/style.css", listDir.getPath() + "/style.css");
    }

    /**
     * 生成数据库列表
     *
     * @author ouyang
     * @since 2014/12/15 17:00
     */
    private void generateTableListHtml() {
        String tableList = indexSb.toString();
        tableList = tableList.replaceAll("###databaseName###", databaseName);

        StringBuilder tableBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : treeMap.entrySet()) {
            tableBuilder.append("<tr><td><a href=\"list/");
            tableBuilder.append(stringStringEntry.getKey());
            tableBuilder.append(".html\">");
            tableBuilder.append(stringStringEntry.getKey());
            tableBuilder.append("</a></td><td>");
            tableBuilder.append(stringStringEntry.getValue());
            tableBuilder.append("</td></tr>");
        }
        tableList = tableList.replaceAll("###tablelList###", tableBuilder.toString());

        StringBuilder tableListHtmlSb = new StringBuilder();
        tableListHtmlSb.append(tableList);

        FileUtil.writeFile(tableListHtmlSb, generateFileDir + '/' + databaseName + ".html");

    }

    /**
     * 生成每一个表的详细列的信息
     *
     * @param tableName 表名称
     * @throws SQLException SQLException
     * @author ouyang
     * @since 2014/12/15 16:59
     */
    private void generateTableHtml(String tableName) throws SQLException {
        String tableList = columnSb.toString();
        tableList = tableList.replace("###databaseName###", databaseName);
        tableList = tableList.replace("###tableName###", tableName);
        tableList = tableList.replace("###tableNameComment###", getTableComment(tableName));
        List<Column> columnList = getColumnList(tableName);
        StringBuilder columnListSb = new StringBuilder();
        for (Column key : columnList) {
            String column = "<tr><td>###columnNo###</td><td>###columnName###</td><td>###isIdentity###</td><td>###isPK###</td><td>###dataType###</td><td>###byteLength###</td><td>###typeLength###</td><td>###scale###</td><td>###isnullable###</td><td>###defaultValue###</td><td>###columnComment###</td></tr>";
            column = column.replace("###columnNo###", key.getColumnNo());
            column = column.replace("###columnName###", key.getColumnName());
            column = column.replace("###isIdentity###", key.getIsIdentity());
            column = column.replace("###isPK###", key.getIsPK());
            column = column.replace("###dataType###", key.getDataType());
            column = column.replace("###byteLength###", key.getByteLength());
            column = column.replace("###typeLength###", key.getTypeLength());
            column = column.replace("###scale###", key.getScale());
            column = column.replace("###isnullable###", key.getIsnullable());
            column = column.replace("###defaultValue###", key.getDefaultValue());
            column = column.replace("###columnComment###", key.getColumnComment());
            columnListSb.append(column);
        }
        tableList = tableList.replaceAll("###columnList###", columnListSb.toString());

        StringBuilder tableListHtmlSb = new StringBuilder();
        tableListHtmlSb.append(tableList);

        FileUtil.writeFile(tableListHtmlSb, generateFileDir + "/list/" + tableName + ".html");
    }

}
