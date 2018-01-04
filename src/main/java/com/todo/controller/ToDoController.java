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
import org.springframework.web.bind.annotation.PathVariable;
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
		toDoService.createItem(item);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * GET /read --> Find a item by id from the database.
	 * @throws ToDoException 
	 */
	@GetMapping("/getitem/{id}")
	public ResponseEntity<?> read(@PathVariable("id") Long id) throws ToDoException {
		ToDo item = toDoService.findItemByID(id);
		return new ResponseEntity<ToDo>(item, HttpStatus.OK);
	}
	
	/**
	 * PUT /update --> Update a item and save it in the database.
	 * @throws ToDoException 
	 */
	@PutMapping("/updateitem")
	public ResponseEntity<?> update(@RequestBody ToDo item) throws ToDoException {
		ToDo updatedTicket = toDoService.editItem(item);
		return new ResponseEntity<ToDo>(updatedTicket, HttpStatus.OK);
	}
	
	/**
	 * DELETE /delete --> Delete a item by id from the database.
	 * @throws ToDoException 
	 */
	@DeleteMapping("/deleteitem/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ToDoException {
		toDoService.deleteItem(id);
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
