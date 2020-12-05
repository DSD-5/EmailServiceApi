package com.tutiempolibro.email.business.impl;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Resources;
import com.tutiempolibro.email.entity.SendEmail;
import com.tutiempolibro.email.entity.SendSalesRent;
import com.tutiempolibro.email.model.Persona;
import com.tutiempolibro.email.repositorio.PersonaRepository;
import com.tutiempolibro.email.service.MailService;
import com.tutiempolibro.email.util.Constante;

@Service
public class RentSalesEmailBusiness {
	
	@Autowired
	private PersonaRepository personaRepo;

	@Autowired
	private MailService mailService;
	
	public SendSalesRent enviarEmailCompraAlquiler(SendSalesRent send) {
		// TODO Auto-generated method stub
		
		Optional<Persona> personaOpt = personaRepo.findById(send.getIdpersona());

		if (personaOpt.isPresent()) {
			Persona persona = personaOpt.get();	
			
			URL url = Resources.getResource("templateSalesRent.txt");
			try {
				
				String text = Resources.toString(url, StandardCharsets.UTF_8);
				
				String body = this.armarcuerpomensaje(send);
				
				text = text.replace("persona.nombres", persona.getNombres());
				text = text.replace("carrido.id", send.getIdcarrito().toString());
				text = text.replace("detalle.body", body);
				
				mailService.sendMail("tutiempolibroupc@hotmail.com", persona.getEmail(), Constante.ASUNTO_COMPRA_FINALIZDA,
						text);
				send.setEnvio(true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			send.setEnvio(false);
		}
		
		return send;
	}

	private String armarcuerpomensaje(SendSalesRent send) {
		// TODO Auto-generated method stub
		StringBuilder strTableInicio = new StringBuilder("<table>");
		StringBuilder strTrInicio = new StringBuilder("<tr>");
		StringBuilder strTdInicio = new StringBuilder("<td>");
		StringBuilder strTdFin = new StringBuilder("</td>");
		StringBuilder strTrFin = new StringBuilder("</tr>");
		StringBuilder strTableFin = new StringBuilder("</table>");
		
		strTableInicio.append(strTrInicio);
		
		if(!send.getCodventa().isEmpty())
		strTableInicio.append(strTdInicio).append("<strong>Cod Venta</strong>").append(strTdFin)
		.append(strTdInicio).append(send.getCodventa()).append(strTdFin).append(strTrFin);
		
		if(!send.getCodalquiler().isEmpty())
			strTableInicio.append(strTdInicio).append("<strong>Cod Alquiler</strong>").append(strTdFin)
			.append(strTdInicio).append(send.getCodalquiler()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Subtotal Venta").append(strTdFin)
		.append(strTdInicio).append("S/."+send.getSubtotalventa()).append(strTdFin).append(strTrFin);
	
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Subtotal Alquiler").append(strTdFin)
		.append(strTdInicio).append("S/."+send.getSubtotalalquiler()).append(strTdFin).append(strTrFin);
	
		strTableInicio.append(strTrInicio).append(strTdInicio).append("(+) Costo Delivery").append(strTdFin)
		.append(strTdInicio).append("S/."+send.getDelivery()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("(-) Descuento Venta").append(strTdFin)
		.append(strTdInicio).append("S/."+send.getTotaldescventa()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("(-) Descuento Alquiler").append(strTdFin)
		.append(strTdInicio).append("S/."+send.getTotaldescventa()).append(strTdFin).append(strTrFin);
		
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("<strong>Total</strong>").append(strTdFin)
		.append(strTdInicio).append("<strong>S/."+send.getTotal()+"<strong>").append(strTdFin).append(strTrFin);
	
		strTableInicio.append(strTrInicio).append("<td colspan='2'>").append("DELIVERY").append(strTdFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Departamento").append(strTdFin)
		.append(strTdInicio).append(send.getDepartamento()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Provincia").append(strTdFin)
		.append(strTdInicio).append(send.getProvince()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Distrito").append(strTdFin)
		.append(strTdInicio).append(send.getDistrito()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Dirección").append(strTdFin)
		.append(strTdInicio).append(send.getDirection()).append(strTdFin).append(strTrFin);
		
		strTableInicio.append(strTrInicio).append(strTdInicio).append("Referencia").append(strTdFin)
		.append(strTdInicio).append(send.getReference()).append(strTdFin).append(strTrFin);
	
		strTableInicio.append(strTableFin);
		
		return strTableInicio.toString();
	}

}
