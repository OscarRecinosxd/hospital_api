package com.grupo25.hospital.services.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.services.MailService;
import com.grupo25.hospital.utils.DynamicTemplatePersonalization;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class MailServiceImpl implements MailService {
		
	@Value("${spring.sendgrid.api-key}")
	private String key;
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	@Override
	public String sendWelcomeEmail(String toEmail, String username) throws IOException {
		Email from = new Email("holistichospital0122@outlook.com");
		Email to = new Email(toEmail);
		Mail mail = new Mail();
		
		DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
		personalization.addTo(to);
		mail.setFrom(from);
		mail.setSubject("Bienvenido/a");
		// Variables de la plantilla
		personalization.addDynamicTemplateData("username", username);
		
		mail.addPersonalization(personalization);
		
		// Id de plantilla
		mail.setTemplateId("d-a87ea05e64a242d69c8d4f78fa551935");
		
		// SendGrid key
		SendGrid sg = new SendGrid(key);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			 logger.info(response.getBody());
			return response.getBody();
		} catch (IOException ex) {
			throw ex;
		}
	}
	
	@Override
	public String sendRequestPasswordEmail(String toEmail, String username, Long id) throws IOException {
				Email from = new Email("holistichospital0122@outlook.com");
				Email to = new Email(toEmail);
				Mail mail = new Mail();
				
				DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
				personalization.addTo(to);
				mail.setFrom(from);
				mail.setSubject("Recuperar contraseña");
				// Variables de la plantilla
				personalization.addDynamicTemplateData("username", username);
				personalization.addDynamicTemplateData("id", id.toString());
				
				mail.addPersonalization(personalization);
				
				// Id de plantilla
				mail.setTemplateId("d-db9275f106d04837a9e5fa6e96504c95");
				
				// SendGrid key
				SendGrid sg = new SendGrid(key);
				Request request = new Request();

				try {
					request.setMethod(Method.POST);
					request.setEndpoint("mail/send");
					request.setBody(mail.build());
					Response response = sg.api(request);
					 logger.info(response.getBody());
					return response.getBody();
				} catch (IOException ex) {
					throw ex;
				}
	}
	
}