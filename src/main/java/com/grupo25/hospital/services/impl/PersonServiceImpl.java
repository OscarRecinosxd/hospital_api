package com.grupo25.hospital.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.UpdatePassDTO;
import com.grupo25.hospital.models.dtos.CreatePersonDTO;
import com.grupo25.hospital.models.dtos.EditPersonDTO;
import com.grupo25.hospital.models.dtos.RestorePassDTO;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Role;
import com.grupo25.hospital.models.entities.Token;
import com.grupo25.hospital.repositories.PersonRepository;
import com.grupo25.hospital.repositories.TokenRepository;
import com.grupo25.hospital.services.PersonService;
import com.grupo25.hospital.utils.TokenManager;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(CreatePersonDTO personInfo, Role role, Area area ) throws Exception {
		Person person = new Person();
				
		person.setName(personInfo.getName());
		person.setUsername(personInfo.getUsername());
		person.setLast_name(personInfo.getLast_name());
		person.setStatus(true);
		person.setEmail(personInfo.getEmail());
		person.setPassword(passEncoder.encode(personInfo.getPassword()));
		person.setGender(personInfo.getGender());
		person.setBirthdate(LocalDate.parse(personInfo.getBirthdate()));
		person.setId_role(role);
		person.setId_area(area);
		
		personRepository.save(person);
	}

	@Override
	public Person findOneById(Long id) throws Exception {
		return personRepository.findById(id).orElse(null);
	}

	@Override
	public List<Person> findAll() throws Exception {
		return personRepository.findAll();
	}

	@Override
	public Boolean comparePassword(Person person, String passToCompare) throws Exception {
		return passEncoder.matches(passToCompare, person.getPassword());
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insertToken(Person person, String token) throws Exception {
		cleanTokens(person);
		
		Token newToken = new Token(token, person);
		
		tokenRepository.save(newToken);
	}
	
	@Transactional(rollbackOn = Exception.class)
	private void cleanTokens(Person person) {
		List<Token> tokens = tokenRepository.findByPersonAndActive(person, true);
		
		tokens.forEach((userToken) -> {
			if(!tokenManager.validateJwtToken(userToken.getContent(), person.getUsername())) {
				userToken.setActive(false);
				tokenRepository.save(userToken);
			}
		});
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Boolean isTokenValid(Person person, String token) throws Exception {
		cleanTokens(person);
	    
	    List<Token> tokens = tokenRepository.findByPersonAndActive(person, true);
	            
	    return tokens.stream()
	    .filter((personToken) -> {
	        return personToken.getContent().equals(token) && personToken.getActive();
	    })
	    .findAny()
	    .orElse(null) != null;
	}

	@Override
	public Person findOneByIdentifier(String username) throws Exception {
		return personRepository.findByUsername(username);
	}

	@Override
	public Person getPersonAuthenticated() throws Exception {
		String username = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		return personRepository.findByUsername(username);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(EditPersonDTO personInfo, Person person) throws Exception {
		if(personInfo.getEmail() != null) person.setEmail(personInfo.getEmail());
		if(personInfo.getStatus() != null) person.setStatus(personInfo.getStatus());
		
		personRepository.save(person);		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deactivate(Person person, Boolean status) throws Exception {
		person.setStatus(!status);
		
		personRepository.save(person);
	}




	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updatePersonPassword(UpdatePassDTO passInfo, Person person) throws Exception {
		person.setPassword(passEncoder.encode(passInfo.getNew_password()));
		
		personRepository.save(person);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void restorePassword(RestorePassDTO restoreInfo, Person person) throws Exception {
		person.setPassword(passEncoder.encode(restoreInfo.getNew_password()));
		
		personRepository.save(person);
	}

	

}
