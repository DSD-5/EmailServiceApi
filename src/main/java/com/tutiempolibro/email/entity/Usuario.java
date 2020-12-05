package com.tutiempolibro.email.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String password;

}
