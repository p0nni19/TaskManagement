package com.projecttask.Entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "TASK")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private long taskId;
	
	@NotNull(message = "Invalid task title")
	@Length(min = 2 , max = 20, message = "Title should be 2 to 20 characters")
	@Column(name = "TITLE")
	private String title;
	
	@Length(max = 25, message = "Desciption should be less than 25 characters.")
	@Column(name = "DESCIPTION")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE")
	private Date dueDate;
	
	@Column(name = "STATUS")
	private String status;
}
