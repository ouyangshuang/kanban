package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求参与的各个成员
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class RelationCaseMember implements Serializable{
	//
	private Integer id;
	//角色代码
	private Long roleCode;
	//
	private Integer memberId;
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
	
	public Long getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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