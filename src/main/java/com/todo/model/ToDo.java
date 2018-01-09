package com.todo.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="TO_DO")
public class ToDo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ToDo(){
		
	}
	
	public ToDo(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="STATUS", nullable=false)
	private Boolean status;
	
	@Transient
	private String errorMessage;
	
	@PersistenceConstructor
	public ToDo(Long id, String description, Boolean status) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
