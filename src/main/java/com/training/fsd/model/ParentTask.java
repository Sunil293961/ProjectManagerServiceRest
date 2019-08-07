/**
 * 
 */
package com.training.fsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 293961
 *
 */
@Entity
@Table(name="parenttask")
public class ParentTask {

	/**
	 * 
	 */
	public ParentTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Parent_ID")
	private int parent_id;
	@Column(name ="Parent_Task")
	private String parent_task;
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_task() {
		return parent_task;
	}
	public void setParent_task(String parent_task) {
		this.parent_task = parent_task;
	}
	@Override
	public String toString() {
		return "ParentTask [parent_id=" + parent_id + ", parent_task=" + parent_task + "]";
	}
	

}
