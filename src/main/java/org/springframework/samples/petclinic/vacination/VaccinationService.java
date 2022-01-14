package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;


@Service
public class VaccinationService {

    @Autowired

    VaccinationRepository vaccinationRepository;

    @Autowired
    Vaccine vaccine;

    public List<Vaccination> getAll(){
        return vaccinationRepository.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return vaccinationRepository.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vaccinationRepository.findVaccine(typeName);
    }


 //   Implementar el método save del servicio de gestión de vacunaciones. Si la mascota especificada en la 
//vacunación no es del tipo asociado a la vacuna correspondiente, se debe lanzar una excepción de tipo 
//UnfeasibleVaccinationException (esta clase está ya creada en el paquete vaccination). Además, en caso 
//de lanzarse la excepción, la transacción asociada a la operación de guardado de la vacunación debe 
//echarse atrás (hacer rollback).

    public Vaccination save(Vaccination v) throws UnfeasibleVaccinationException {
        PetType petType = vaccine.getPetType();
        if(v.getVaccinatedPet().equals(petType)){
            return vaccinationRepository.save(v); 

        }else {
            throw new UnfeasibleVaccinationException();
            
        }
              
    }

    
}
