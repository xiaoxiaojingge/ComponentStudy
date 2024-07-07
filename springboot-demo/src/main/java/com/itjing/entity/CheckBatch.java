package com.itjing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description：
 * @Author: xiaojinggege
 * @Date: 2022/3/10/010 14:03
 */
public class CheckBatch implements Serializable {

	public static final long serialVersionUID = 1L;

	private String id;

	private String taskName;

	private int checkScope;

	private String startTime;

	private String endTime;

	private String orgCode;

	private int taskState;

	private String readyCount;

	private String alreadyCount;

	private String failCount;

	private String createUser;

	private Date createTime;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setCheckScope(int checkScope) {
		this.checkScope = checkScope;
	}

	public int getCheckScope() {
		return checkScope;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setReadyCount(String readyCount) {
		this.readyCount = readyCount;
	}

	public String getReadyCount() {
		return readyCount;
	}

	public void setAlreadyCount(String alreadyCount) {
		this.alreadyCount = alreadyCount;
	}

	public String getAlreadyCount() {
		return alreadyCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
