package com.grupo25.hospital.services.impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.services.PersonService;

@Service
public class AuthPersonDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	PersonService personService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Person personFound = personService.findOneByIdentifier(username);
		    if(personFound != null) {
		        return new org.springframework.security.core.userdetails.User(
		                    personFound.getUsername(),
		                    personFound.getPassword(),
		                    new ArrayList<>()
		                );
		    }else {
		        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		    }
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);

		}
	}

	

}
