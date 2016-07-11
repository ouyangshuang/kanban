package com.lianjia.sh.kanban.tools.code;

import java.io.Serializable;

/**
 * 列信息对象
 *
 * @author ouyang
 * @since 2015-02-09 14:11
 */
public class Column implements Serializable {

    private String columnNo; //'字段序号'
    private String columnName; //'字段名'
    private String isIdentity; //'标识'
    private String isPK;//'主键'
    private String dataType;//'类型'
    private String byteLength;//'占用字节数'
    private String typeLength;//'长度'
    private String scale;//'小数位数'
    private String isnullable;//'允许空'
    private String defaultValue;//'默认值'
    private String columnComment;//'字段说明'

    private String javaType;//对应的java类型
    private boolean deprecated;//是否已废弃 约定 已废弃 加上“已废弃”注释


    public String getIsPK() {
        return isPK;
    }

    public void setIsPK(String isPK) {
        this.isPK = isPK;
    }

    public String getByteLength() {
        return byteLength;
    }

    public void setByteLength(String byteLength) {
        this.byteLength = byteLength;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(String columnNo) {
        this.columnNo = columnNo;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(String isIdentity) {
        this.isIdentity = isIdentity;
    }

    public String getIsnullable() {
        return isnullable;
    }

    public void setIsnullable(String isnullable) {
        this.isnullable = isnullable;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getTypeLength() {
        return typeLength;
    }

    public void setTypeLength(String typeLength) {
        this.typeLength = typeLength;
    }
}
