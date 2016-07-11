package com.lianjia.sh.kanban.bean;

/**
 * @author ouyang
 * @since 2016-07-08 10:28
 */
public enum DictEnum {

    角色("role",10000L),
    需求类型("caseType",10010L),
    流程阶段("caseStage",10020L),
    需求优先级("caseLevel",10030L);

    DictEnum(String ename, Long parentCode) {
        this.ename = ename;
        this.parentCode = parentCode;
    }

    String ename;
    Long parentCode;

    public static DictEnum getByParentCode(Long parentCode){
        for (DictEnum dictEnum : DictEnum.values()) {
            if (dictEnum.getParentCode().equals(parentCode)) {
                return dictEnum;
            }
        }
        return null;
    }

    public static DictEnum getByEname(String ename){
        for (DictEnum dictEnum : DictEnum.values()) {
            if (dictEnum.getEname().equals(ename)) {
                return dictEnum;
            }
        }
        return null;
    }

    public static DictEnum getByName(String name){
        for (DictEnum dictEnum : DictEnum.values()) {
            if (dictEnum.name().equals(name)) {
                return dictEnum;
            }
        }
        return null;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Long getParentCode() {
        return parentCode;
    }

    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

}
