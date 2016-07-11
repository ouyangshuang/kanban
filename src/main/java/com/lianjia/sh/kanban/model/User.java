package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class User implements Serializable{
	//
	private Integer id;
	//
	private String username;
	//
	private String password;
	//
	private Date ctime;
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
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