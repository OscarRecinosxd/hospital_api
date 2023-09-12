package com.grupo25.hospital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo25.hospital.models.dtos.UpdatePassDTO;
import com.grupo25.hospital.models.dtos.CreateAreaDTO;
import com.grupo25.hospital.models.dtos.CreateDrugDTO;
import com.grupo25.hospital.models.dtos.CreateInmunizationDTO;
import com.grupo25.hospital.models.dtos.CreatePersonDTO;
import com.grupo25.hospital.models.dtos.CreateTestDTO;
import com.grupo25.hospital.models.dtos.CreateVaccineDTO;
import com.grupo25.hospital.models.dtos.EditAreaDTO;
import com.grupo25.hospital.models.dtos.EditDrugDTO;
import com.grupo25.hospital.models.dtos.EditInmunizationDTO;
import com.grupo25.hospital.models.dtos.EditPersonDTO;
import com.grupo25.hospital.models.dtos.EditTestDTO;
import com.grupo25.hospital.models.dtos.EditVaccineDTO;
import com.grupo25.hospital.models.dtos.MessageDTO;
import com.grupo25.hospital.models.dtos.PersonResponseDTO;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Drug;
import com.grupo25.hospital.models.entities.Inmunization;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Role;
import com.grupo25.hospital.models.entities.Shift;
import com.grupo25.hospital.models.entities.Test;
import com.grupo25.hospital.models.entities.Vaccine;
import com.grupo25.hospital.services.AreaService;
import com.grupo25.hospital.services.DrugService;
import com.grupo25.hospital.services.InmunizationService;
import com.grupo25.hospital.services.MailService;
import com.grupo25.hospital.services.PersonService;
import com.grupo25.hospital.services.RoleService;
import com.grupo25.hospital.services.ShiftService;
import com.grupo25.hospital.services.TestService;
import com.grupo25.hospital.services.VaccineService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
		
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private VaccineService vaccService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private InmunizationService inmuService;
	
	@Value("${spring.sendgrid.api-key}")
	private String key;
	
	/*
	 *USERS CRUD 
	*/
	
	@GetMapping("/users")
	public ResponseEntity<?> findAllPeople(){
		try {
			List<Person> people = personService.findAll();
			
			return new ResponseEntity<>(
						people,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/users/{id}")
	@ResponseBody
	public ResponseEntity<?> findOnePerson(@PathVariable(name = "id") Long id){
		try {
			Person foundPerson = personService.findOneById(id);

			if(foundPerson != null) {
				return new ResponseEntity<>(
						new PersonResponseDTO(foundPerson.getEmail(), foundPerson.getStatus()),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new PersonResponseDTO(),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/users/create")
	public ResponseEntity<?> registerUser(@Valid @RequestBody CreatePersonDTO personInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Person foundPerson = personService.findOneByIdentifier(personInfo.getUsername());
			
			if(foundPerson != null) {
				return new ResponseEntity<>(
						new MessageDTO("Esta persona ya existe"),
						HttpStatus.BAD_REQUEST);
			}
			
			//mailService.sendWelcomeEmail(personInfo.getEmail(), personInfo.getUsername());
			Role foundRole = roleService.findOneById(personInfo.getRole());
			
			if(personInfo.getArea() == null) {
				personService.register(personInfo, foundRole, null);
				return new ResponseEntity<>(
						new MessageDTO("Persona registrada"),
						HttpStatus.CREATED);
			}else {
				Area foundArea = areaService.findOneById(personInfo.getArea());
				
				personService.register(personInfo, foundRole, foundArea);
				
				return new ResponseEntity<>(
						new MessageDTO("Persona registrada"),
						HttpStatus.CREATED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/users/update")
	public ResponseEntity<?> editUser(@Valid @RequestBody EditPersonDTO personInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Person foundPerson = personService.findOneById(personInfo.getId());
			
			if(foundPerson != null) {
				personService.update(personInfo, foundPerson);
				
				return new ResponseEntity<>(
						new MessageDTO("Persona actualizada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Persona no encontrada"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/users/{id}/deactivate")
	public ResponseEntity<?> deactivateUser(@PathVariable(name = "id") Long id){
		try {
			Person foundPerson = personService.findOneById(id);
			
			if(foundPerson != null) {
				Boolean status = foundPerson.getStatus();
				personService.deactivate(foundPerson, status);
				return new ResponseEntity<>(
						new MessageDTO("Persona actualizada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Persona no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	/*
	 *AREAS CRUD
	 */
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
	
	@GetMapping("/areas/{id}")
	public ResponseEntity<?> findOneArea(@PathVariable(name = "id") Long id){
		try {
			Area foundArea = areaService.findOneById(id);

			if(foundArea != null) {
				return new ResponseEntity<>(
						foundArea,
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Área no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/areas/create")
	public ResponseEntity<?> createArea(@Valid @RequestBody CreateAreaDTO areaInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Area foundArea = areaService.findOneByIdentifier(areaInfo.getName());
			
			if(foundArea != null) {
				return new ResponseEntity<>(
						new MessageDTO("Esta área ya existe"),
						HttpStatus.BAD_REQUEST);
			}
			
			Shift foundShift = shiftService.findOneById(areaInfo.getId_shift());
			
			if(foundShift == null) {
				return new ResponseEntity<>(
						new MessageDTO("Turno no encontrado"),
						HttpStatus.NOT_FOUND);
			}
			
			areaService.insert(areaInfo, foundShift);
			
			return new ResponseEntity<>(
					new MessageDTO("Área registrada"),
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/areas/update")
	public ResponseEntity<?> updateArea(@Valid @RequestBody EditAreaDTO areaInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Area foundArea = areaService.findOneById(areaInfo.getId());
			
			Shift foundShift = shiftService.findOneById(areaInfo.getId_shift());
			
			if(foundShift == null) {
				return new ResponseEntity<>(
						new MessageDTO("Turno no encontrado"),
						HttpStatus.NOT_FOUND);
			}
			
			if(foundArea != null && foundShift != null) {
				areaService.update(areaInfo, foundArea,foundShift);
				return new ResponseEntity<>(
						new MessageDTO("Área actualizada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Área no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@DeleteMapping("/areas/{id}/delete")
	public ResponseEntity<?> deleteArea(@PathVariable(name = "id") Long id){
		try {
			Area foundArea = areaService.findOneById(id);
			
			if(foundArea != null) {
				areaService.delete(foundArea);
				
				return new ResponseEntity<>(
						new MessageDTO("Área eliminada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Área no encontrada"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}
	
	/*
	 * INMUNIZATIONS CRUD
	 */
	@GetMapping("/inmunizations")
	public ResponseEntity<?> getAllInmunizations(){
		try {
			List<Inmunization> inmunizations = inmuService.findAll();
			
			return new ResponseEntity<>(
						inmunizations,
						HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/inmunizations/{id}")
	public ResponseEntity<?> findOneInmunization(@PathVariable(name = "id") Long id){
		try {
			Inmunization foundInmu = inmuService.findOneById(id);

			if(foundInmu != null) {
				return new ResponseEntity<>(
						foundInmu,
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Inmunización no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/inmunizations/create")
	public ResponseEntity<?> createInmunization(@Valid @RequestBody CreateInmunizationDTO inmuInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Vaccine vacc = vaccService.findOneById(inmuInfo.getId_vaccine());
			
			if(vacc == null) {
				return new ResponseEntity<>(
						new MessageDTO("Vacuna no encontrada"),
						HttpStatus.NOT_FOUND
						);
			}
			
			inmuService.insert(inmuInfo, vacc);
			
			
			return new ResponseEntity<>(
					new MessageDTO("Inmunización registrada"),
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/inmunizations/update")
	public ResponseEntity<?> updateInmunization(@Valid @RequestBody EditInmunizationDTO inmuInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Inmunization foundInmu = inmuService.findOneById(inmuInfo.getId_inmunization());
			
			if(foundInmu != null) {
				inmuService.update(inmuInfo, foundInmu);
				return new ResponseEntity<>(
						new MessageDTO("Inmunización actualizada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Inmunización no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@DeleteMapping("/inmunizations/{id}/delete")
	public ResponseEntity<?> deleteInmunization(@PathVariable(name = "id") Long id){
		try {
			Inmunization foundInmu = inmuService.findOneById(id);
			
			if(foundInmu != null) {
				inmuService.delete(foundInmu);
				
				return new ResponseEntity<>(
						new MessageDTO("Inmunización eliminada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Inmunización no encontrada"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}
	
	/*
	 * VACCINES CRUD
	 */
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
	
	@GetMapping("/vaccines/{id}")
	public ResponseEntity<?> findOneVaccine(@PathVariable(name = "id") Long id){
		try {
			Vaccine foundVaccine = vaccService.findOneById(id);

			if(foundVaccine != null) {
				return new ResponseEntity<>(
						foundVaccine,
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Vacuna no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/vaccines/create")
	public ResponseEntity<?> createVaccine(@Valid @RequestBody CreateVaccineDTO vaccInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Vaccine vacc = vaccService.findOneByIdentifier(vaccInfo.getName());
			
			if(vacc != null) {
				return new ResponseEntity<>(
						new MessageDTO("Esta vacuna ya existe"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			vaccService.insert(vaccInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Vacuna registrada"),
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/vaccines/update")
	public ResponseEntity<?> updateVaccine(@Valid @RequestBody EditVaccineDTO vaccInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Vaccine foundVacc = vaccService.findOneById(vaccInfo.getId());
			
			if(foundVacc != null) {
				vaccService.update(vaccInfo, foundVacc);
				return new ResponseEntity<>(
						new MessageDTO("Vacuna actualizada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Vacuna no encontrada"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@DeleteMapping("/vaccines/{id}/delete")
	public ResponseEntity<?> deleteVaccine(@PathVariable(name = "id") Long id){
		try {
			Vaccine foundVacc = vaccService.findOneById(id);
			
			if(foundVacc != null) {
				vaccService.delete(foundVacc);
				
				return new ResponseEntity<>(
						new MessageDTO("Vacuna eliminada"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Vacuna no encontrada"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}
	
	/*
	 * DRUGS CRUD
	*/
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
	
	@GetMapping("/drugs/{id}")
	public ResponseEntity<?> findOneDrug(@PathVariable(name = "id") Long id){
		try {
			Drug foundDrug = drugService.findOneById(id);

			if(foundDrug != null) {
				return new ResponseEntity<>(
						foundDrug,
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Medicamento no encontrado"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/drugs/create")
	public ResponseEntity<?> createDrug(@Valid @RequestBody CreateDrugDTO drugInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Drug foundDrug = drugService.findOneByIdentifier(drugInfo.getName());
			
			if(foundDrug != null) {
				return new ResponseEntity<>(
						new MessageDTO("Este medicamento ya existe"),
						HttpStatus.BAD_REQUEST);
			}
			
			drugService.insert(drugInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Medicamento registrado"),
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/drugs/update")
	public ResponseEntity<?> updateDrug(@Valid @RequestBody EditDrugDTO drugInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Drug foundDrug = drugService.findOneById(drugInfo.getId());
			
			if(foundDrug != null) {
				drugService.update(drugInfo, foundDrug);
				
				return new ResponseEntity<>(
						new MessageDTO("Medicamento actualizado"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Medicamento no encontrado"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@DeleteMapping("/drugs/{id}/delete")
	public ResponseEntity<?> deleteDrug(@PathVariable(name = "id") Long id){
		try {
			Drug foundDrug = drugService.findOneById(id);
			
			if(foundDrug != null) {
				drugService.delete(foundDrug);
				
				return new ResponseEntity<>(
						new MessageDTO("Medicamento eliminado"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Medicamento no encontrado"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
				);
		}
	}
	
	/*
	 * TESTS CRUD
	*/
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
	
	@GetMapping("/tests/{id}")
	public ResponseEntity<?> findOneTest(@PathVariable(name = "id") Long id){
		try {
			Test foundTest = testService.findOneById(id);

			if(foundTest != null) {
				return new ResponseEntity<>(
						foundTest,
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Examen no encontrado"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/tests/create")
	public ResponseEntity<?> createTest(@Valid @RequestBody CreateTestDTO testInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Test foundTest = testService.findOneByIdentifier(testInfo.getName());
			
			if(foundTest != null) {
				return new ResponseEntity<>(
						new MessageDTO("Este examen ya existe"),
						HttpStatus.BAD_REQUEST);
			}
			
			testService.insert(testInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Examen registrado"),
					HttpStatus.CREATED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageDTO("Error interno"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PutMapping("/tests/update")
	public ResponseEntity<?> updateTest(@Valid @RequestBody EditTestDTO testInfo, BindingResult result){
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new MessageDTO("Errores en validacion" + errors),
						HttpStatus.BAD_REQUEST);
			}
			
			Test foundTest = testService.findOneById(testInfo.getId());
			
			if(foundTest != null) {
				testService.update(testInfo, foundTest);
				
				return new ResponseEntity<>(
						new MessageDTO("Examen actualizado"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Examen no encontrado"),
					HttpStatus.NOT_FOUND
				);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
						null,
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@DeleteMapping("/tests/{id}/delete")
	public ResponseEntity<?> deleteTest(@PathVariable(name = "id") Long id){
		try {
			Test foundTest = testService.findOneById(id);
			
			if(foundTest != null) {
				testService.delete(foundTest);
				
				return new ResponseEntity<>(
						new MessageDTO("Examen eliminado"),
						HttpStatus.OK
					);
			}
			
			return new ResponseEntity<>(
					new MessageDTO("Examen no encontrado"),
					HttpStatus.NOT_FOUND
				);
		} catch(Exception e) {
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
	
}
