package com.training.fsd;

import org.springframework.web.bind.annotation.RestController;
import com.training.fsd.model.ParentTask;
import com.training.fsd.model.Project;
import com.training.fsd.model.Task;
import com.training.fsd.model.User;
import com.training.fsd.service.ProjectManagerService;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ProjectManagerController {
	//logger object creation.  This would help in identifying the flow
	Logger logger = LoggerFactory.getLogger(ProjectManagerController.class);
	@Autowired
	private ProjectManagerService service;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	///////////////////////////////////
	/////////// USer Section /////////
	/////////////////////////////////
	// looks good
	@PostMapping(value = "/addUser", produces = "application/json")
	public User addUser(@RequestBody User user) {
		logger.debug("adding user from Controller");
		User uOne = new User();
		uOne = service.createUser(user);
		logger.debug("added successfully...");
		return uOne;
	}

	// looks good
	@GetMapping(value = "/getUsers", produces = "application/json")
	public List<User> getallUsers() {
		logger.debug("getallUsers users from Controller");
		List<User> users = new ArrayList<User>();
		users = service.getUsers();
		logger.debug("retrieved the user successfully...");
		return users;
	}

	//Working fine.
	@GetMapping(value = "/getUser/{user_id}", produces = "application/json")
	public User getUserById(@PathVariable("user_id") int user_id) {
		logger.debug("fetch users from Controller");
		User uOne = new User();
		uOne = service.getUserById(user_id).get();
		logger.debug("retrieveed the user successfully...");
		return uOne;
	}

	// working fine.
	@PutMapping(value = "/updateUser", produces = "application/json")
	public User updateUser(@RequestBody User user) {
		logger.debug("update user from Controller");
		User uOne = new User();
		uOne = service.updateUser(user);
		logger.debug("updated succesfully..");
		return uOne;
	}

	//working fine
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	///////////////////////////////////
	/////////// Project Section //////
	/////////////////////////////////

	// Looks good.
	@GetMapping(value = "/getProjects", produces = "application/json")
	public List<Project> getProjectDetails() {
		logger.debug("calling the service...");
		List<Project> projects = new ArrayList<Project>();
		projects = service.getProjects();
		return projects;
	}

	// Looks good
	@GetMapping(value = "/getProject/{project_id}", produces = "application/json")
	public Project getProjectDetailsById(@PathVariable("project_id") int project_id) {
		logger.debug("calling the service...");
		return service.getProjectsById(project_id).get();
	}

	// needs some correction
	@PostMapping(value = "/createProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public Project createProject(@RequestBody Project project, @PathVariable("user_id") int user_id) {
		logger.debug("create Project");
		User user = service.getUserById(user_id).get();
		logger.debug("create Project:" + project.getEndDate());
		logger.debug("create Project:" + project.getPriority());
		logger.debug("create Project:" + project.getStartDate());
		logger.debug("create Project:" + project.getProject());

		project.setUser(user);
		return service.createProject(project);
	}
	//need to test
	@PutMapping(value= "/updateProject/{user_id}", produces="application/json")
	public Project updateProject(@RequestBody Project project, @RequestParam("user_id") int user_id) {
		logger.debug("update Project");
		User user = service.getUserById(user_id).get();
		project.setUser(user);
		Project updtProject = service.updateProject(project);
		return updtProject;

	}
	//working fine.
	@DeleteMapping(value = "projects/{user_id}")
	public void deleteProject(@PathVariable("user_id") int user_id) {
		logger.debug("delete project..");
		service.deleteProject(user_id);
	}
	///////////////////////////////////
	/////////// Task Section /////////
	/////////////////////////////////
	//working fine
	@PostMapping(value="/createTask", produces="application/json")
	public Task createTask(@RequestBody Task task, @RequestParam("parent_id") int parent_id,
			@RequestParam("project_id") int project_id, @RequestParam("user_id") int user_id) {

		logger.debug("create Task");
		ParentTask parentTask = service.getParentTaskById(parent_id);
		Project project = service.getProjectsById(project_id).get();
		User user = service.getUserById(user_id).get();
		task.setParentTask(parentTask);
		task.setProject(project);
		task.setUser(user);
		return service.createTask(task);
	}

	@PutMapping(value="/updateTask", produces="application/json")
	public Task updateTask(@RequestBody Task task, @RequestParam("parent_id") int parent_id,
			@RequestParam("project_id") int project_id, @RequestParam("user_id") int user_id) {
		logger.debug("update Task");
		ParentTask parentTask = service.getParentTaskById(parent_id);
		Project project = service.getProjectsById(project_id).get();
		User user = service.getUserById(user_id).get();
		task.setParentTask(parentTask);
		task.setProject(project);
		task.setUser(user);
		return service.updateTask(task);
	}
	//working fine
	@GetMapping(value ="/getTasks", produces="application/json")
	public List<Task> getAllTasks() {
		logger.debug("get All Task");
		List<Task> taskList = new ArrayList<Task>();
		taskList = service.findAllTasks();
		return taskList;
	}
	// Working fine.
	@GetMapping(value="/getTask/{task_id}", produces="application/json")
	public Task getTasksById(@PathVariable("task_id") int task_id) {
		logger.debug("getTasksById");
		return service.findTaskById(task_id).get();
	}
	// working fine.
	@GetMapping(value="/getTasksByPorjectId", produces="application/json")
	public List<Task> getAllTasksByProjectId(@RequestParam("project_id") int projectId) {
		logger.debug("get All Task by ProjectId");
		return service.findTaskByProjectId(projectId);
	}
	///////////////////////////////////
	/////////// ParentTask Section ///
	/////////////////////////////////
	//working fine.
	@PostMapping(value="/createParentTask", produces="application/json")
	public ParentTask createParentTask(@RequestBody ParentTask parentTask) {
		logger.debug("create Parent task");
		return service.createParentTask(parentTask);
	}
	//working fine
	@GetMapping(value="/getParentTask", produces="application/json")
	public ParentTask getParentTaskById(@RequestParam("parent_id") int parentId) {
		logger.debug("get Parent task");
		return service.findParentTaskById(parentId).get();
	}
	//working fine
	@GetMapping(value="/getParentTasks", produces="application/json")
	public List<ParentTask> getAllParentTasks() {
		logger.debug("get Parent tasks");
		return service.findAllParentTasks();
	}

}
