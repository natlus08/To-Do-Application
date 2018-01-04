package com.todo.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.todo.model.ToDo;
import com.todo.repository.ToDoRepository;
import com.todo.exception.ToDoException;

@Service
public class ToDoServiceImpl implements ToDoService {

	public static final Logger logger = LoggerFactory
			.getLogger(ToDoServiceImpl.class);

	@Autowired
	private ToDoRepository toDoRepository;

	@Autowired
	private Environment environment;
	
	@Override
	public void createItem(ToDo item) throws ToDoException {
		toDoRepository.save(item);
	}

	@Override
	public ToDo findItemByID(Long id) throws ToDoException {
		ToDo item = toDoRepository.findOne(id);
		if (item == null) {
			throw new ToDoException("Item with id " + id
					+ " not found.");
		}
		return item;
	}

	@Override
	public ToDo editItem(ToDo item) throws ToDoException {
		ToDo updatedItem = null;
		if (toDoRepository.exists(item.getId())) {
			updatedItem = toDoRepository.save(item);
		} else {
			throw new ToDoException("Unable to Update. Item with id "
					+ item.getId() + " not found.");
		}
		return updatedItem;
	}
	
	@Override
	public void deleteItem(Long id) throws ToDoException {
		toDoRepository.delete(id);
	}

	@Override
	public List<ToDo> readAll() throws ToDoException {
		return toDoRepository.findAll();
	}

}
