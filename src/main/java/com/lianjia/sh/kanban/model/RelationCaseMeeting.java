package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求的会议
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class RelationCaseMeeting implements Serializable{
	//需求会议关系
	private Integer id;
	//
	private Integer caseId;
	//
	private Integer meetingId;
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
	
	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	
	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
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