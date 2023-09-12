package com.grupo25.hospital.services;

import java.io.IOException;

public interface MailService {
	String sendWelcomeEmail(String toEmail, String username) throws IOException;
	String sendRequestPasswordEmail(String toEmail, String username, Long id) throws IOException;
}
