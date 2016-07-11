package com.lianjia.sh.kanban.tools.code;

import com.lianjia.sh.kanban.tools.code.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ouyang
 * @since 2015-02-09 11:02
 */
@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class JavaBeanCodeGenerate extends CodeGenerate {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaBeanCodeGenerate.class);

    //表名
    private String tableName;
    //表注释
    private String tableComment;
    //包名
    private final String packageStr;
    //作者
    private final String codeGenerateAuthor;
    //文件信息
    private final StringBuilder sb = new StringBuilder();
    //列信息
    private List<Column> columnList = new ArrayList<>();


    /**
     * 生成java bean
     *
     * @param connection         数据库连接
     * @param tableName          表名
     * @param packageStr         生成的javabean 包路径
     * @param codeGenerateAuthor 生成作者
     * @throws SQLException SQLException
     */
    public JavaBeanCodeGenerate(Connection connection, String tableName, String packageStr, String codeGenerateAuthor) throws SQLException {
        super(connection);
        this.tableName = tableName;
        this.packageStr = packageStr;
        this.codeGenerateAuthor = codeGenerateAuthor;

        //初始化jdbcToJavaMap
        initJdbcToJavaMap();
        //初始化表注释
        tableComment = getTableComment(tableName);
        //初始化列
        columnList = getColumnList(tableName);

    }


    /**
     * 生成java bean 与数据库一一对应
     * @author ouyang
     * @since 2015-02-12 17:41
     */
    public void generate() {
        //处理java类名，首字母大写
        tableName = format(tableName);

        //写入包名
        generatePackage();
        //导入类
        generateImportClass();
        //生成类注释
        generateClassComment();
        //添加属性 不支持 boolean  isXxx
        generateField();
        //添加getter setter 方法
        generateGetterSetter();

        //noinspection AccessOfSystemProperties
        String paths = System.getProperty("user.dir");
        String javaFile = "";
        if(System.getProperty("os.name").toUpperCase().contains("Windows")){
            javaFile = paths + "\\src\\main\\java\\"
                    + packageStr.replace("/", "\\").replace(".", "\\")
                    + '\\' + firstLetterUpperCase(tableName)
                    + ".java";
        }else {
            javaFile = paths + "/src/main/java/"
                    + packageStr.replace("/", "/").replace(".", "/")
                    + '/' + firstLetterUpperCase(tableName)
                    + ".java";
        }

        FileUtil.writeFile(sb, javaFile);
    }


    /**
     * 生成包目录字符串
     *
     * @author ouyang
     * @since 2014/12/9 17:02
     */
    public void generatePackage() {
        sb.append("package " + packageStr + ';');
        sb.append(LINE);
        sb.append(LINE);
    }

    /**
     * 生成import语句字符串
     *
     * @author ouyang
     * @since 2014/12/9 16:59
     */
    private void generateImportClass() {
        //序列化
        sb.append("import java.io.Serializable;");
        sb.append(LINE);
        //每个表都有createdAt字段 都加上日期
        sb.append("import java.util.Date;");
        sb.append(LINE);
        sb.append(LINE);
    }

    /**
     * 添加ClassComment 注释 可覆盖，自行添加注释
     *
     * @author ouyang
     * @since 2015-02-11 09:44
     */
    private void generateClassComment() {
        //生成文件注释和类名定义
        sb.append("/**" + LINE);
        sb.append(" * " + tableComment + LINE);
        sb.append(" * " + LINE);
        sb.append(" * @author " + codeGenerateAuthor + " code generate" + LINE);
        sb.append(" * @since " + getDateString(new Date()) + LINE);
        sb.append(" */" + LINE);
        sb.append("@SuppressWarnings(\"UnusedDeclaration\")" + LINE);
        sb.append("public class " + firstLetterUpperCase(tableName) + " implements Serializable{");
        sb.append(LINE);
    }

    /**
     * 生成属性 成员变量的字符串
     *
     * @author ouyang
     * @since 2014/12/9 16:59
     */
    private void generateField() {
        for (Column column : columnList) {

            if (column.getColumnComment() != null) {
                sb.append(TAB);
                sb.append("//" + column.getColumnComment());
                sb.append(LINE);

                //已经废弃的加上已废弃声明
                if (column.isDeprecated()) {
                    sb.append(TAB);
                    sb.append("@Deprecated");
                    sb.append(LINE);
                }
            } else {
                if ("id".equals(column.getColumnName()) ||
                        "createdAt".equals(column.getColumnName()) ||
                        "houseId".equals(column.getColumnName()) ||
                        "propertyId".equals(column.getColumnName()) ||
                        "updatedAt".equals(column.getColumnName())) {
                    sb.append(TAB);
                    sb.append("//code generate " + column.getColumnName());
                    sb.append(LINE);
                } else {
                    sb.append(TAB);
                    sb.append("//TODO " + column.getColumnName() + "未加注释");
                    sb.append(LINE);
                }
            }

            sb.append(TAB);

            sb.append("private " + column.getJavaType() + ' ' + column.getColumnName() + ';');
            sb.append(LINE);
        }
    }

    /**
     * 生成属性的get set方法 字符串
     *
     * @author ouyang
     * @since 2014/12/9 16:58
     */
    private void generateGetterSetter() {
        for (Column column : columnList) {

            sb.append(TAB);

            String getName = "public " + column.getJavaType() + " get" + firstLetterUpperCase(column.getColumnName()) + "() {";
            sb.append(LINE).append(TAB).append(getName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("return " + column.getColumnName() + ';');
            sb.append(LINE).append(TAB).append('}');
            sb.append(LINE);

            String setName = "public void set" + firstLetterUpperCase(column.getColumnName()) + '(' + column.getJavaType() + ' ' + column.getColumnName() + ") {";
            sb.append(LINE).append(TAB).append(setName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("this." + column.getColumnName() + " = " + column.getColumnName() + ';');
            sb.append(LINE).append(TAB).append('}');
            sb.append(LINE);
        }

        sb.append('}');
    }

    /**
     * 得到日期的字符串
     *
     * @param date java.util.Date
     * @return "yyyy-MM-dd";
     * @author ouyang
     * @since 2014/12/11 16:55
     */
    public static String getDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
