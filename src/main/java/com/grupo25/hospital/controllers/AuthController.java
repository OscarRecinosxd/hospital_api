package com.grupo25.hospital.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo25.hospital.models.dtos.CreatePersonDTO;
import com.grupo25.hospital.models.dtos.LoginDTO;
import com.grupo25.hospital.models.dtos.MessageDTO;
import com.grupo25.hospital.models.dtos.RequestPasswordDTO;
import com.grupo25.hospital.models.dtos.RestorePassDTO;
import com.grupo25.hospital.models.dtos.TokenDTO;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Role;
import com.grupo25.hospital.services.AreaService;
import com.grupo25.hospital.services.MailService;
import com.grupo25.hospital.services.PersonService;
import com.grupo25.hospital.services.RoleService;
import com.grupo25.hospital.utils.TokenManager;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
	                    HttpStatus.BAD_REQUEST
	                );
	        }
			
			Person person = personService.findOneByIdentifier(loginInfo.getUsername());
			
			if(person == null) {
				return new ResponseEntity<>(
	                    new TokenDTO(),
	                    HttpStatus.NOT_FOUND
	                );
			}
			
			if(personService.comparePassword(person, loginInfo.getPassword()) == false || person.getStatus() == false	) {
	            return new ResponseEntity<>(
	                    new TokenDTO(),
	                    HttpStatus.UNAUTHORIZED
	                );
	        }
			
	        
	        final String token = tokenManager.generateJwtToken(person.getUsername());
	        
	        personService.insertToken(person, token);
	        
	        return new ResponseEntity<TokenDTO>(
                    new TokenDTO(
                    		token,
                    		person.getId_person(),
                    		person.getName(),
                    		person.getUsername(),
                    		person.getLast_name(),
                    		person.getEmail(),
                    		person.getGender(),
                    		person.getStatus(),
                    		person.getBirthdate(),
                    		person.getId_role(),
                    		person.getId_area()
                    	),
                    HttpStatus.OK
                );
	        
		} catch (Exception e) {
	        return new ResponseEntity<>(
	                new TokenDTO(),
	                HttpStatus.INTERNAL_SERVER_ERROR
	            );
		}
	}
	
	@PostMapping("/request-password")
	public ResponseEntity<?> requestPassword(@Valid @RequestBody RequestPasswordDTO requestInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
	            return new ResponseEntity<>(
	                    new TokenDTO(),
	                    HttpStatus.BAD_REQUEST
	                );
	        }
			
			Person foundPerson = personService.findOneByIdentifier(requestInfo.getUsername());
			
			if(foundPerson == null) {
				return new ResponseEntity<>(
	                    new MessageDTO("Persona no encontrada"),
	                    HttpStatus.NOT_FOUND
	                );
			}
			
			mailService.sendRequestPasswordEmail(
					foundPerson.getEmail(), 
					foundPerson.getUsername(), 
					foundPerson.getId_person()
				);
			
	        return new ResponseEntity<>(
                    new MessageDTO("Verifique su bandeja de entrada de su correo electrónico"),
                    HttpStatus.OK
                );
	        
		} catch (Exception e) {
	        return new ResponseEntity<>(
	                null,
	                HttpStatus.INTERNAL_SERVER_ERROR
	            );
		}
	}
	
	@PostMapping("/restore-password")
	public ResponseEntity<?> restorePassword(@Valid @RequestBody RestorePassDTO restorePassInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			if(!restorePassInfo.getNew_password().equals(restorePassInfo.getConfirm_password())) {
				return new ResponseEntity<>(
						new MessageDTO("Las contraseñas no coinciden"),
						HttpStatus.BAD_REQUEST);
			}
			
			Person foundPerson = personService.findOneById(restorePassInfo.getId());
			
			personService.restorePassword(restorePassInfo, foundPerson);
			
			return new ResponseEntity<>(
					new MessageDTO("Contraseña actualizada"),
					HttpStatus.OK
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}
	
	
}