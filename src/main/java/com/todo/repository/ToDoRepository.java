package com.todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.model.ToDo;
@Transactional
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	
	
}
