package com.pie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

@Entity
@Table(name="scheduler_job")
public class SchedulerJob extends IdEntity{
	private static final long serialVersionUID = 1L;

	/** 任务名称 */
	private String jobName;
	/** 任务分组 */
	private String jobGroup;
	/** 任务状态 0禁用 1启用 2删除*/
	private String jobStatus;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String jobDesc;
	/** 具体执行任务的全类名 */
	private String className;
	
	private String businessClassName;
	private String businessId;

	@Column(name = "job_name")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	@Column(name = "job_group")
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	@Column(name = "job_status")
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	@Column(name = "cron_expression")
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	@Column(name = "job_desc")
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	@Column(name = "class_name")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Column(name = "business_class_name")
	public String getBusinessClassName() {
		return businessClassName;
	}
	public void setBusinessClassName(String businessClassName) {
		this.businessClassName = businessClassName;
	}
	@Column(name = "business_id")
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
