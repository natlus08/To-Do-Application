/**
 * 
 */
package com.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.exception.ToDoException;
import com.todo.model.ToDo;
import com.todo.service.ToDoService;

@RestController
@RequestMapping("/api")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;
	
	public static final Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	
	/**
	 * POST /create --> Create a new item and save it in the database.
	 * @throws ToDoException 
	 */
	@PostMapping("/createitem")
	public ResponseEntity<?> create(@RequestBody ToDo item) throws ToDoException {
		ToDo newItem = toDoService.createItem(item);
		return new ResponseEntity<ToDo>(newItem, HttpStatus.OK);
	}
	
	/**
	 * PUT /update --> Update a item and save it in the database.
	 * @throws ToDoException 
	 */
	@PutMapping("/updateitem")
	public ResponseEntity<?> update(@RequestBody ToDo item) throws ToDoException {
		ToDo updatedItem = toDoService.editItem(item);
		return new ResponseEntity<ToDo>(updatedItem, HttpStatus.OK);
	}
	
	/**
	 * DELETE /delete --> Delete all items by status from the database.
	 * @throws ToDoException 
	 */
	@DeleteMapping("/deleteitems")
	public ResponseEntity<?> delete() throws ToDoException {
		toDoService.deleteItems();
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * GET /read --> Read all tickets from the database.
	 */
	@GetMapping("/getallitems")
	public ResponseEntity<List<ToDo>> readAll() throws ToDoException {
		List<ToDo> tickets = toDoService.readAll();
		if (tickets.isEmpty()) {
			return new ResponseEntity<List<ToDo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ToDo>>(tickets, HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(ToDoException.class)
	public ResponseEntity<?> ToDoExceptionHandler(ToDoException exception){
		return new ResponseEntity<ToDo>(new ToDo(exception.getMessage()),HttpStatus.CONFLICT);
	}
}
