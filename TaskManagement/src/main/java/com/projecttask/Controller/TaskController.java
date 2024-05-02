package com.projecttask.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projecttask.Entity.Task;
import com.projecttask.Exception.TaskSystemException;
import com.projecttask.Service.TaskService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@PostMapping
	public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) throws TaskSystemException {
		if (task != null) {
			return ResponseEntity.ok(taskService.addTask(task));
		} else {
			throw new TaskSystemException();
		}
	}

	@PostMapping("/addAll")
	public ResponseEntity<String> addTasks(@Valid @RequestBody List<Task> tasks) throws TaskSystemException{
		if (tasks != null) {
			return ResponseEntity.ok(taskService.addTasks(tasks));
		} else {
			throw new TaskSystemException();
		}
	}
	
	@GetMapping("/{id}")
	public Task findTaskById(@PathVariable long id) throws TaskSystemException {
		try{
			return taskService.findTaskById(id);
		} catch(Exception e) {
			throw new TaskSystemException();
		}
	}
	
	@GetMapping
	public List<Task> findAllTask() throws TaskSystemException{
		try {
			return taskService.findAllTask();
		} catch(Exception e) {
			throw new TaskSystemException();
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) throws TaskSystemException {
		if (task != null) {
			return ResponseEntity.ok(taskService.updateTask(task));
		} else {
			throw new TaskSystemException();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable long id) throws TaskSystemException {
		try{
			return ResponseEntity.ok(taskService.deleteTaskById(id));
		} catch(Exception e) {
			throw new TaskSystemException();
		}
	}
	
	@GetMapping("/sortTask")
	public List<Task> sortTask() throws TaskSystemException{
		try{
			return taskService.sortTask();
		} catch(Exception e) {
			throw new TaskSystemException();
		}
	}
	
	@GetMapping("/getTaskByStatus/{status}")
	public List<Task> getTaskByStatus(@PathVariable String status) throws TaskSystemException{
		try {
			return taskService.getTaskByStatus(status);
		} catch (Exception e) {
			throw new TaskSystemException();
		}
	}
}
