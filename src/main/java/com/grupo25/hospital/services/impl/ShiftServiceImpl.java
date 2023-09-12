package com.grupo25.hospital.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.entities.Shift;
import com.grupo25.hospital.repositories.ShiftRepository;
import com.grupo25.hospital.services.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private ShiftRepository shiftRepo;
	
	@Override
	public List<Shift> findAll() throws Exception {
		return shiftRepo.findAll();
	}

	@Override
	public Shift findOneById(Long id) throws Exception {
		return shiftRepo.findById(id).orElse(null);
	}

}
