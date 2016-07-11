package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class Dict implements Serializable{
	//
	private Integer id;
	//名称
	private String name;
	//英文名称
	private String ename;
	//描述
	private String desc;
	//
	private Long code;
	//所有一级父类为0   0生万物
	private Long parentCode;
	//
	private String key;
	//
	private String value;
	//
	private Integer cm;
	//
	private Date ctime;
	//
	private Integer um;
	//
	private Date utime;
	//
	private Integer status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	
	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getCm() {
		return cm;
	}

	public void setCm(Integer cm) {
		this.cm = cm;
	}
	
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	public Integer getUm() {
		return um;
	}

	public void setUm(Integer um) {
		this.um = um;
	}
	
	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}