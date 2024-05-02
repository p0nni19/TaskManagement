package com.projecttask.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.projecttask.Entity.Task;
import com.projecttask.Exception.TaskSystemException;
import com.projecttask.Repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task addTask(Task task) throws TaskSystemException{
		try {
			return taskRepository.save(task);
		} catch (Exception e) {
			throw new TaskSystemException();
		}
	}
	
	public String addTasks(List<Task> tasks) throws TaskSystemException{
		try {
			taskRepository.saveAll(tasks);
			return "All task Added";
		} catch (Exception e) {
			throw new TaskSystemException();
		}
	}
	
	public Task findTaskById(long id) throws TaskSystemException {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isPresent()) {
			return task.get();
		} else {
			throw new TaskSystemException("ID NOT FOUND");
		}
	}
	
	public List<Task> findAllTask(){
		return taskRepository.findAll();
	}
	
	public Task updateTask(Task task) throws TaskSystemException {
		Optional<Task> optTask = taskRepository.findById(task.getTaskId());
		if(optTask.isPresent()) {
			Task existingTask = optTask.get();
			existingTask.setTitle(task.getTitle());
			existingTask.setDescription(task.getDescription());
			existingTask.setDueDate(task.getDueDate());
			existingTask.setStatus(task.getStatus());
			return existingTask;
		}else {
			throw new TaskSystemException("ID NOT FOUND");
		}
	}
	
	public String deleteTaskById(long id) throws TaskSystemException {
		Optional<Task> task = taskRepository.findById(id);
		if(task.isPresent()) {
			taskRepository.deleteById(id);
			return "Task Deleted : "+id; 
		} else {
			throw new TaskSystemException("ID NOT FOUND");
		}
	}
	
	public List<Task> sortTask(){
		List<Task> tasks = taskRepository.findAll();
		if(null != tasks) {
			Collections.sort(tasks, new Comparator<Task>() {
				@Override
				public int compare(Task t1, Task t2) {
					return Long.compare(t1.getTaskId(), t2.getTaskId());
				}
			});
		}
		return tasks;
	}
	
	public List<Task> getTaskByStatus(String status) throws TaskSystemException{
		try {
			if(status != null) {
				return taskRepository.findByStatusOrderByTaskIdDesc(status);
			}
		} catch (Exception e) {
			throw new TaskSystemException();
		}
		return null;
	}

}
