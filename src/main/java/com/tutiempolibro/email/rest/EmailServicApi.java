package com.tutiempolibro.email.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutiempolibro.email.business.SendEmailBusiness;
import com.tutiempolibro.email.entity.SendEmail;
import com.tutiempolibro.email.entity.SendSalesRent;

@RequestMapping("/v1/email")
@RestController
public class EmailServicApi {
	
	@Autowired
	private SendEmailBusiness sendEmailBusiness;
	
	@PostMapping(value = "/envioregusuario", produces = { "application/json" })
	public SendEmail enviarRegUsuario(@RequestBody SendEmail send) {
		return sendEmailBusiness.enviarEmailRegistro(send);
	}
	
	@PostMapping(value = "/enviofinalizarcompra", produces = { "application/json" })
	public SendSalesRent enviarFinalizarCompra(@RequestBody SendSalesRent send) {
		return sendEmailBusiness.enviarEmailCompraAlquiler(send);
	}

}
