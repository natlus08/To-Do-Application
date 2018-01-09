package com.todo.service;

import java.util.List;

import com.todo.exception.ToDoException;
import com.todo.model.ToDo;

/**
 * 
 * @author 387090
 *
 */
public interface ToDoService {

	ToDo createItem (ToDo toDo) throws ToDoException;

	ToDo editItem(ToDo item) throws ToDoException;

	List<ToDo> readAll() throws ToDoException;
	
	void deleteItems() throws ToDoException;
	
}
