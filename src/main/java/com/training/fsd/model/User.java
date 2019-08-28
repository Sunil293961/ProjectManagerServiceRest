/**
 * 
 */
package com.training.fsd.model;

import java.io.Serializable;
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
@Table(name="user")
public class User implements Serializable {


	private static final long serialVersionUID = 1L;
	/**
	 * default constructor
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_id;
	private String firstName;
	private String lastName;
	private String employee_id;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", employee_id="
				+ employee_id + "]";
	}
	
	
	

}
