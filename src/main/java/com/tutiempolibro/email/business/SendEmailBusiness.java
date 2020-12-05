package com.tutiempolibro.email.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutiempolibro.email.business.impl.RegisterUserEmailBusiness;
import com.tutiempolibro.email.business.impl.RentSalesEmailBusiness;
import com.tutiempolibro.email.entity.SendEmail;
import com.tutiempolibro.email.entity.SendSalesRent;

@Service
public class SendEmailBusiness {

	@Autowired
	private RegisterUserEmailBusiness registerUserEmailBusiness;

	@Autowired
	private RentSalesEmailBusiness rentSalesEmailBusiness;

	public SendEmail enviarEmailRegistro(SendEmail send) {

		return registerUserEmailBusiness.enviarEmailRegistro(send);

	}

	public SendSalesRent enviarEmailCompraAlquiler(SendSalesRent send) {

		return rentSalesEmailBusiness.enviarEmailCompraAlquiler(send);

	}

}
