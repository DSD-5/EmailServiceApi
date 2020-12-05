package com.tutiempolibro.email.entity;

import lombok.Data;

@Data
public class SendSalesRent {

	private Integer idpersona;
	
	private Integer idcarrito;
	
	private String total;

	private String direction;

	private String reference;

	private String departamento;

	private String province;

	private String distrito;

	private String delivery;

	private String totaldescventa;

	private String totaldescalquier;

	private String codventa;

	private String codalquiler;

	private String subtotalventa;

	private String subtotalalquiler;
	
	private boolean envio;

}
