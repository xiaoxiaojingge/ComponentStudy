package com.itjing.task.domain;

/**
 * 动态定时任务
 */
public class DynamicJob {

	/**
	 * 任务id
	 */
	private Integer jobId;

	/**
	 * 业务id
	 */
	private Integer businessId;

	/**
	 * Bean名称
	 */
	private String beanName;

	/**
	 * 方法名称
	 */
	private String methodName;

	/**
	 * 方法参数
	 */
	private String methodParams;

	/**
	 * cron表达式
	 */
	private String cronExpression;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 状态（0：正常，1：暂停）
	 */
	private Byte jobStatus;

	/**
	 * 创建人
	 */
	private String createUser;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName == null ? null : beanName.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams) {
		this.methodParams = methodParams == null ? null : methodParams.trim();
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression == null ? null : cronExpression.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Byte getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Byte jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", jobId=").append(jobId);
		sb.append(", businessId=").append(businessId);
		sb.append(", beanName=").append(beanName);
		sb.append(", methodName=").append(methodName);
		sb.append(", methodParams=").append(methodParams);
		sb.append(", cronExpression=").append(cronExpression);
		sb.append(", remark=").append(remark);
		sb.append(", jobStatus=").append(jobStatus);
		sb.append(", createUser=").append(createUser);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		DynamicJob other = (DynamicJob) that;
		return (this.getJobId() == null ? other.getJobId() == null : this.getJobId().equals(other.getJobId()))
				&& (this.getBusinessId() == null ? other.getBusinessId() == null
						: this.getBusinessId().equals(other.getBusinessId()))
				&& (this.getBeanName() == null ? other.getBeanName() == null
						: this.getBeanName().equals(other.getBeanName()))
				&& (this.getMethodName() == null ? other.getMethodName() == null
						: this.getMethodName().equals(other.getMethodName()))
				&& (this.getMethodParams() == null ? other.getMethodParams() == null
						: this.getMethodParams().equals(other.getMethodParams()))
				&& (this.getCronExpression() == null ? other.getCronExpression() == null
						: this.getCronExpression().equals(other.getCronExpression()))
				&& (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
				&& (this.getJobStatus() == null ? other.getJobStatus() == null
						: this.getJobStatus().equals(other.getJobStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getJobId() == null) ? 0 : getJobId().hashCode());
		result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
		result = prime * result + ((getBeanName() == null) ? 0 : getBeanName().hashCode());
		result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
		result = prime * result + ((getMethodParams() == null) ? 0 : getMethodParams().hashCode());
		result = prime * result + ((getCronExpression() == null) ? 0 : getCronExpression().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getJobStatus() == null) ? 0 : getJobStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		return result;
	}

}