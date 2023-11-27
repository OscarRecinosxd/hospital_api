package com.grupo25.hospital.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo25.hospital.models.dtos.MessageDTO;
import com.grupo25.hospital.models.dtos.ScheduleAppointmentDTO;
import com.grupo25.hospital.models.dtos.UpdatePassDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Appointment_type;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Prescription;
import com.grupo25.hospital.models.entities.Shift;
import com.grupo25.hospital.models.entities.Test;
import com.grupo25.hospital.models.entities.Vaccine;
import com.grupo25.hospital.services.AppointmentService;
import com.grupo25.hospital.services.AppointmentTypeService;
import com.grupo25.hospital.services.AreaService;
import com.grupo25.hospital.services.InmunizationService;
import com.grupo25.hospital.services.PersonService;
import com.grupo25.hospital.services.PrescriptionService;
import com.grupo25.hospital.services.ShiftService;
import com.grupo25.hospital.services.TestService;
import com.grupo25.hospital.services.VaccineService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientController {
	@Autowired
	private VaccineService vaccService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private AppointmentTypeService appService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private InmunizationService inmuService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@GetMapping("/appointment-types")
	public ResponseEntity<?> getAllAppointmentTypes(){
		try {
			List<Appointment_type> appsTypes = appService.findAll();
			
			return new ResponseEntity<>(
						appsTypes,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/shifts")
	public ResponseEntity<?> getAllShifts(){
		try {
			List<Shift> shifts = shiftService.findAll();
			
			return new ResponseEntity<>(
						shifts,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
		
	@GetMapping("/vaccines")
	public ResponseEntity<?> getAllVaccines(){
		try {
			List<Vaccine> vaccines = vaccService.findAll();
			
			return new ResponseEntity<>(
						vaccines,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/areas")
	public ResponseEntity<?> getAllAreas(){
		try {
			List<Area> areas = areaService.findAll();
			
			return new ResponseEntity<>(
						areas,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/tests")
	public ResponseEntity<?> getAllTests(){
		try {
			List<Test> tests = testService.findAll();
			
			return new ResponseEntity<>(
						tests,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/my-info/updatepassword")
	public ResponseEntity<?> updateOwnPassword(@Valid @RequestBody UpdatePassDTO newPassInfo, BindingResult result){
		
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			if(!newPassInfo.getNew_password().equals(newPassInfo.getConfirm_password())) {
				return new ResponseEntity<>(
						new MessageDTO("Contraseñas no son iguales"),
						HttpStatus.BAD_REQUEST);
			}
			Person foundPerson = personService.getPersonAuthenticated();
			if(personService.comparePassword(foundPerson, newPassInfo.getCurrent_password())==false) {
				return new ResponseEntity<>(
						new MessageDTO("Contraseña actual equivocada"),
						HttpStatus.BAD_REQUEST);
			}
			personService.updatePersonPassword(newPassInfo, foundPerson);
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
	
	
	@GetMapping("/expediente/inmunizaciones")
	public ResponseEntity<?> getInmunizations(){
		try {
			
			Person paciente = personService.getPersonAuthenticated();
			System.out.println("AAAAAAAA");
			List<Appointment> inmunizationList = inmuService.getAppointmentsByID(paciente.getId_person());
			System.out.println("BBBBBBB");			
			if(inmunizationList.size() == 0) {
				return new ResponseEntity<>(
						new MessageDTO("No hay inmunizaciones aplicadas"),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Appointment>>(
					inmunizationList,
					HttpStatus.OK);
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/expediente/examenes-realizados")
	public ResponseEntity<?> getDoneTests(){
		try {
			
			Person paciente = personService.getPersonAuthenticated();
			
			List<Appointment> testList = testService.getTestByPatient(paciente.getId_person());
			
			if(testList.size() == 0) {
				return new ResponseEntity<>(
						new MessageDTO("No hay exámenes aplicadas"),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Appointment>>(
					testList,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/expediente/citas-previas")
	public ResponseEntity<?> getPreviousAppointments(){
		try {
			Person paciente = personService.getPersonAuthenticated();
			
			List<Appointment> prevAppointments = appointmentService.getPrevAppointments(paciente.getId_person());
			
			if(prevAppointments.size() == 0) {
				return new ResponseEntity<>(
						new MessageDTO("No hay citas previas"),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Appointment>>(
					prevAppointments,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/expediente/recetas-medicas")
	public ResponseEntity<?> getPrescriptions(){
		try {
			Person paciente = personService.getPersonAuthenticated();
			
			List<Prescription> nextAppointments = prescriptionService.getPatientPrescriptions(paciente.getId_person());
			
			if(nextAppointments == null) {
				return new ResponseEntity<>(
						new MessageDTO("No hay recetas médicas"),
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(
					nextAppointments,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("schedule-appointment")
	public ResponseEntity<?> bookAppointment(@Valid @RequestBody ScheduleAppointmentDTO newSchedule, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Person foundPerson = personService.getPersonAuthenticated();
			
			
			Appointment_type type = appService.findOneById(newSchedule.getType()); 
			if(type ==null) {
				return new ResponseEntity<MessageDTO>(
						new MessageDTO("Tipo de cita inválido"),
						HttpStatus.BAD_REQUEST
					);
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime searchDate = LocalDateTime.parse(newSchedule.getDate(), formatter);
			
			//Id de doctor se asigna hasta que el doctor atiende a la persona en una consulta.
			if(type.getId_appointment_type() == 1) {
				Vaccine vaccine = vaccService.findOneById(newSchedule.getIdVAT());
				if (vaccine == null ) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Tipo de vacuna inválido"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				int totalAppointmentsByDateAndVaccine = appointmentService.countAppointmentByDateAndVaccine(searchDate, vaccine);
				
				if(totalAppointmentsByDateAndVaccine >= 6) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Ya no hay más cupos disponibles para el día y turno seleccionado"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				appointmentService.registerInmu(newSchedule, type, vaccine, foundPerson);
				return new ResponseEntity<MessageDTO>(
						new MessageDTO("Cita agendada"),
						HttpStatus.CREATED
					);
			}
			if(type.getId_appointment_type() == 2) {
				Area area = areaService.findOneById(newSchedule.getIdVAT());
				if (area == null ) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Tipo de cita inválido"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				int totalAppointmentsByDateAndArea = appointmentService.countAppointmentByDateAndArea(searchDate, area);
				
				if(totalAppointmentsByDateAndArea >= 6) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Ya no hay más cupos disponibles para el día y turno seleccionado"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				appointmentService.registerArea(newSchedule, type, area, foundPerson);
				
				return new ResponseEntity<MessageDTO>(
						new MessageDTO("Cita agendada"),
						HttpStatus.CREATED
					);
			}
			if(type.getId_appointment_type() == 3) {
				Test test = testService.findOneById(newSchedule.getIdVAT());
				if (test == null ) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Tipo de test inválido"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				int totalAppointmentsByDateAndTest = appointmentService.countAppointmentByDateAndTest(searchDate, test);
				
				if(totalAppointmentsByDateAndTest >= 6) {
					return new ResponseEntity<MessageDTO>(
							new MessageDTO("Ya no hay más cupos disponibles para el día y turno seleccionado"),
							HttpStatus.BAD_REQUEST
						);
				}
				
				appointmentService.registerTest(newSchedule, type, test, foundPerson);
				
				return new ResponseEntity<MessageDTO>(
						new MessageDTO("Cita agendada"),
						HttpStatus.CREATED
					);
			}
			
			return new ResponseEntity<MessageDTO>(
					new MessageDTO("Tipo de cita inválido"),
					HttpStatus.BAD_REQUEST
				);
			
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(
			HttpStatus.INTERNAL_SERVER_ERROR);
}
		}
	}

