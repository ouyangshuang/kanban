package com.lianjia.sh.kanban.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求
 * 
 * @author ouyang code generate
 * @since 2016-07-07
 */
@SuppressWarnings("UnusedDeclaration")
public class Case implements Serializable{
	//
	private Integer id;
	//需求名称
	private String name;
	//禅道id
	private Integer chandaoId;
	//
	private String desc;
	//产品需求 技术故事 线上bug 插入需求  1-4
	private Long type;
	//需求池   开发池  开发中  待测试  测试中  待发布   上线  1-7
	private Long stage;
	//低 中 高     1   4    7 
	private Long level;
	//单位：天  比如 ：  0.5d
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
	private Date status;
	//需求提出时间
	private Date proposeAt;
	//需求移交预计时间
	private Date transferExpectAt;
	//需求移交实际时间
	private Date transferReallyAt;
	//开发时间
	private Date codeAt;
	//开发完成预计时间
	private Date codeEndExpectAt;
	//开发完成实际时间
	private Date codeEndReallyAt;
	//qa时间
	private Date qaAt;
	//qa完成预计时间
	private Date qaEndExpectAt;
	//qa完成实际时间
	private Date qaEndReallyAt;
	//上线预期时间
	private Date onlineExpectAt;
	//上线实际时间
	private Date onlineReallyAt;
	
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
	
	public Integer getChandaoId() {
		return chandaoId;
	}

	public void setChandaoId(Integer chandaoId) {
		this.chandaoId = chandaoId;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}
	
	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
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
	
	public Date getStatus() {
		return status;
	}

	public void setStatus(Date status) {
		this.status = status;
	}
	
	public Date getProposeAt() {
		return proposeAt;
	}

	public void setProposeAt(Date proposeAt) {
		this.proposeAt = proposeAt;
	}
	
	public Date getTransferExpectAt() {
		return transferExpectAt;
	}

	public void setTransferExpectAt(Date transferExpectAt) {
		this.transferExpectAt = transferExpectAt;
	}
	
	public Date getTransferReallyAt() {
		return transferReallyAt;
	}

	public void setTransferReallyAt(Date transferReallyAt) {
		this.transferReallyAt = transferReallyAt;
	}
	
	public Date getCodeAt() {
		return codeAt;
	}

	public void setCodeAt(Date codeAt) {
		this.codeAt = codeAt;
	}
	
	public Date getCodeEndExpectAt() {
		return codeEndExpectAt;
	}

	public void setCodeEndExpectAt(Date codeEndExpectAt) {
		this.codeEndExpectAt = codeEndExpectAt;
	}
	
	public Date getCodeEndReallyAt() {
		return codeEndReallyAt;
	}

	public void setCodeEndReallyAt(Date codeEndReallyAt) {
		this.codeEndReallyAt = codeEndReallyAt;
	}
	
	public Date getQaAt() {
		return qaAt;
	}

	public void setQaAt(Date qaAt) {
		this.qaAt = qaAt;
	}
	
	public Date getQaEndExpectAt() {
		return qaEndExpectAt;
	}

	public void setQaEndExpectAt(Date qaEndExpectAt) {
		this.qaEndExpectAt = qaEndExpectAt;
	}
	
	public Date getQaEndReallyAt() {
		return qaEndReallyAt;
	}

	public void setQaEndReallyAt(Date qaEndReallyAt) {
		this.qaEndReallyAt = qaEndReallyAt;
	}
	
	public Date getOnlineExpectAt() {
		return onlineExpectAt;
	}

	public void setOnlineExpectAt(Date onlineExpectAt) {
		this.onlineExpectAt = onlineExpectAt;
	}
	
	public Date getOnlineReallyAt() {
		return onlineReallyAt;
	}

	public void setOnlineReallyAt(Date onlineReallyAt) {
		this.onlineReallyAt = onlineReallyAt;
	}
}