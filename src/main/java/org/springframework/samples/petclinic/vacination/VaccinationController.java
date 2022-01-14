package org.springframework.samples.petclinic.vacination;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccinationController {


    @Autowired
    private VaccinationService vaccinationService;
    
    @Autowired
    private PetService petService;


    @ModelAttribute("vaccines")
    public Collection<Vaccine> populateVaccines() {
	return vaccinationService.getAllVaccines();
    }

    @ModelAttribute("pets")
    public Collection<Pet> populatePets() {
	return petService.findAllPets();
    }


    @GetMapping("/vaccination/create")
    public String initCreationForm(Map<String, Object> model) {
	Vaccination vaccination = new Vaccination();
	model.put("vaccination", vaccination);
	return "vaccination/createOrUpdateVaccinationForm";
    }

   // Se propone crear un método de controlador en la clase anterior que responda a peticiones tipo post en 
//la url y se encargue de validar los datos de la vacunación, mostrar los errores encontrados si existieran a 
//través del formulario, y si no existen errores, almacenar la vacunación a través del servicio de gestión de 
//vacunas. Tenga en cuenta que, si la mascota seleccionada no es de un tipo coherente con la vacuna a 
//inocular, debe capturarse la excepción correspondiente y mostrar el mensaje error “La mascota 
//seleccionada no puede recibir la vacuna especificada.” en el campo de la vacuna seleccionada del 
//formulario. Tras grabar la vacunación, en caso de éxito, se mostrará la página de inicio de la aplicación 
//(welcome), sin usar redirección. Recuerde que el formato de entrada de la fecha de la vacunación debe 
//ser “yyyy/MM/dd” en caso contrario las pruebas no pasarán (puede usar como ejemplo la clase Pet y su 
//fecha de nacimiento para especificar dicho formato).

@PostMapping("/vaccination/create")
    public String processCreationForm(@Valid Vaccination vaccination, BindingResult result) {
	if(result.hasErrors()) {
	    return "vaccination/createOrUpdateVaccinationForm";
	} else {
	    vaccinationService.save(vaccination);
	    return "welcome";
	}
    }
    
}
