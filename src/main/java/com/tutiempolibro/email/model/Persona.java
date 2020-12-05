package com.tutiempolibro.email.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="persona")
@Data
public class Persona {
	
	@Id
	private Integer idpersona;

	private String nombres;

	private String apepat;
	private String apemat;
	@Column(unique = true)
	private String email;
}
