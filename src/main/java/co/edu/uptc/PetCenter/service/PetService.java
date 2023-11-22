package co.edu.uptc.PetCenter.service;

import co.edu.uptc.PetCenter.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    Pet savePet(Pet pet);
    List<Pet> getAllPets();
    Optional<Pet> getPetById(Long id);
    Pet updatePet(Pet pet, Long id);
    boolean deletePet(Long id);
}
