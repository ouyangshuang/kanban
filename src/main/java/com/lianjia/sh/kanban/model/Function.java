package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求功能分解
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class Function implements Serializable{
	//
	private Integer id;
	//
	private String name;
	//
	private String desc;
	//
	private Float estimateTime;
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
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Float getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(Float estimateTime) {
		this.estimateTime = estimateTime;
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