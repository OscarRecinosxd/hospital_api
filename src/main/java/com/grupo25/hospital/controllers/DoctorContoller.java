package com.grupo25.hospital.controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo25.hospital.models.dtos.AddPrescriptionDTO;
import com.grupo25.hospital.models.dtos.AppoinmentIdDTO;
import com.grupo25.hospital.models.dtos.CreatePrescriptionDTO;
import com.grupo25.hospital.models.dtos.EndAppointmentDTO;
import com.grupo25.hospital.models.dtos.ExpedienteDTO;
import com.grupo25.hospital.models.dtos.GetEntityDTO;
import com.grupo25.hospital.models.dtos.MessageDTO;
import com.grupo25.hospital.models.dtos.PrescriptionDTO;
import com.grupo25.hospital.models.dtos.PrescriptionInfoDTO;
import com.grupo25.hospital.models.dtos.UpdatePassDTO;
import com.grupo25.hospital.models.dtos.UserPrescriptionDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Drug;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Prescription;
import com.grupo25.hospital.repositories.AppointmentRepository;
import com.grupo25.hospital.repositories.DrugRepository;
import com.grupo25.hospital.repositories.PrescriptionRepository;
import com.grupo25.hospital.services.AppointmentService;
import com.grupo25.hospital.services.DrugService;
import com.grupo25.hospital.services.PersonService;
import com.grupo25.hospital.services.PrescriptionService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*")
public class DoctorContoller {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private AppointmentService appointService;
	
	@Autowired
	private PrescriptionService prescService;
			
	@GetMapping("/drugs")
	public ResponseEntity<?> getAllDrugs(){
		try {
			List<Drug> drugs = drugService.findAll();
			
			return new ResponseEntity<>(
						drugs,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/appointments/today")
	public ResponseEntity<?> getTodayAppointments(){
		LocalDateTime timestamp = (LocalDate.now().atStartOfDay());
		LocalDateTime timestamp2 = (timestamp.plusDays(1)).truncatedTo(ChronoUnit.DAYS);
		try {
			List<Appointment> appointments = appointmentService.findTodayAppointments(timestamp.truncatedTo(ChronoUnit.DAYS), timestamp2);
			
			if(appointments == null) {
				return new ResponseEntity<>(
						new MessageDTO("No hay citas para hoy"),
						HttpStatus.NOT_FOUND
					);
			}
			
			return new ResponseEntity<>(
					appointments,
					HttpStatus.OK
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@PostMapping("/citas-dia/consulta/receta/crear")
	public ResponseEntity<?> createPrescription(@RequestBody PrescriptionDTO receta,BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			Person foundPerson = personService.getPersonAuthenticated();
			Appointment foundAppointment = appointService.getById(receta.getId_appointment());
			
			if(foundAppointment == null) {
				return new ResponseEntity<>(
						new MessageDTO("Cita no encontrada"),
						HttpStatus.NOT_FOUND);
			}
			Drug foundDrug = drugService.findOneById(receta.getMedicine());
			
			if(foundDrug == null) {
				return new ResponseEntity<>(
						new MessageDTO("Medicamento no encontrado"),
						HttpStatus.NOT_FOUND);
			}
			prescService.create(foundDrug, receta, foundAppointment);
			
			return new ResponseEntity<>(
					new MessageDTO("Receta creada"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/citas-dia/consulta/detalles-cita")
	public ResponseEntity<?> appointmentDetails(@RequestBody CreatePrescriptionDTO receta,BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			Person foundPerson = personService.getPersonAuthenticated();
			Appointment foundAppointment = appointService.getById(receta.getId_appointment());
			
			if(foundAppointment == null) {
				return new ResponseEntity<>(
						new MessageDTO("Cita no encontrada"),
						HttpStatus.NOT_FOUND);
			}
			if(receta.getMedicines().size() != 0) {
				for (int i = 0; i < receta.getMedicines().size(); i++) {
				    Drug drug = drugService.findOneById(receta.getMedicines().get(i).getMedicine());
				    PrescriptionInfoDTO p = new PrescriptionInfoDTO();
				    p.setDoses(receta.getMedicines().get(i).getDoses());
				    p.setQuantity(receta.getMedicines().get(i).getQuantity());
				    p.setIndication(receta.getIndication());
				    prescService.insert(foundPerson,foundAppointment, drug, p);
				}
				appointmentService.insertAppointmentDetails(foundAppointment, receta.getIndication());
			} else {
			    PrescriptionInfoDTO p = new PrescriptionInfoDTO();
			    p.setIndication(receta.getIndication());
				prescService.insert(foundPerson,foundAppointment, null, p);
				appointmentService.insertAppointmentDetails(foundAppointment, receta.getIndication());
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Receta creada"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/citas-dia/consulta/expediente/{id}")
	public ResponseEntity<List<ExpedienteDTO>> getUserExpediente(@PathVariable Long id){
		try {
			
			List<Appointment> GetAppointments = appointmentService.getAllAppointments(id);
			
			List<ExpedienteDTO> Expediente = new ArrayList<>();
			
			GetAppointments.forEach(appointment ->{
				
				ExpedienteDTO a = new ExpedienteDTO();
				a.setTipo(appointment.getId_appointment_type().getType_name());
				a.setDetallesCita(appointment.getAppointment_details());
				a.setFecha(appointment.getTimestamp());
				
				Expediente.add(a);
				
			});

			return new ResponseEntity<List<ExpedienteDTO>>(
					Expediente,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/citas-dia/consulta/finalizar/{id}")
	public ResponseEntity<?> endUpAppointment(@PathVariable(name = "id") Long id,@RequestBody EndAppointmentDTO appointment,BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			Person foundPerson = personService.getPersonAuthenticated();
			Appointment foundAppointment = appointmentService.getById(id);
			
			if(foundAppointment != null) {
				appointmentService.endUpAppointment(foundAppointment,appointment,foundPerson);
				
				return new ResponseEntity<>(
						new MessageDTO("Cita finalizada con éxito"),
						HttpStatus.OK);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Cita no encontrada"),
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
