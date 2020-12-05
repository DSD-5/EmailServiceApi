package com.tutiempolibro.email.business.impl;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Resources;
import com.tutiempolibro.email.entity.SendEmail;
import com.tutiempolibro.email.model.Persona;
import com.tutiempolibro.email.repositorio.PersonaRepository;
import com.tutiempolibro.email.service.MailService;
import com.tutiempolibro.email.util.Constante;

@Service
public class RegisterUserEmailBusiness {

	@Autowired
	private PersonaRepository personaRepo;

	@Autowired
	private MailService mailService;

	public SendEmail enviarEmailRegistro(SendEmail send) {

		Optional<Persona> personaOpt = personaRepo.findById(send.getIdpersona());

		if (personaOpt.isPresent()) {
			Persona persona = personaOpt.get();			
			
			URL url = Resources.getResource("template.txt");
			try {
				
				String text = Resources.toString(url, StandardCharsets.UTF_8);
				text = text.replace("persona.nombres", persona.getNombres());
				text = text.replace("usuario.usuario", send.getUsuario().getUsuario());
				text = text.replace("usuario.password", send.getUsuario().getPassword());
				mailService.sendMail("tutiempolibroupc@hotmail.com", persona.getEmail(), Constante.ASUNTO_REGISTRO_USUARIO,
						text);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			send.setEnvio(true);	
		}else {
			send.setEnvio(false);
		}

		return send;

	}

}
