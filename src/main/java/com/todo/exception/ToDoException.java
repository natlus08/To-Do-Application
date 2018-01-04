/**
 * 
 */
package com.todo.exception;

/**
 * @author 387090
 *
 */
public class ToDoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ToDoException() {
		super();
	}
	
	public ToDoException(String msg) {
		super(msg);
	}
	
	public ToDoException(Throwable thr) {
		super(thr);
	}
	
	public ToDoException(String msg,Throwable thr) {
		super(msg, thr);
	}
}
