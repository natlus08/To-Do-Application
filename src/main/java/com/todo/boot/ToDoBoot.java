/**
 * 
 */
package com.todo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 387090
 *
 */
@SpringBootApplication(scanBasePackages = { "com.todo"})
public class ToDoBoot {

	/**
	 * Acts as the launch point for the entire application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ToDoBoot.class, args);
	}

}
