/**
 * 
 */
package com.training.fsd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 293961
 *
 */
@Entity
@Table(name="task")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Task_ID")
	private int task_id;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ParentTask.class)
	@JoinColumn(name = "Parent_ID")
	private ParentTask parentTask;
	@ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "Project_ID")
	private Project project;
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "User_ID")
	private User user;
	@Column(name="Task")
	private String task;
	private Date startDate;
	private Date endDate;
	private int priority;
	private String  status;
	
	public ParentTask getParentTask() {
		return parentTask;
	}
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", parentTask=" + parentTask + ", project=" + project + ", user=" + user
				+ ", task=" + task + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
				+ ", status=" + status + "]";
	}
	
	

}
