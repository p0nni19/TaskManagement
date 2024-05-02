package com.projecttask.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projecttask.Entity.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
		public List<Task> findByStatusOrderByTaskIdDesc(String status);
}
