package co.edu.uptc.PetCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uptc.PetCenter.model.Pet;
import co.edu.uptc.PetCenter.service.PetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<String> createPet(@RequestBody Pet pet) {
        Pet createdPet = petService.savePet(pet);
        if (createdPet != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mascota creada Exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallo al crear mascota");
        }
    }


    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        if (!pets.isEmpty()) {
            return ResponseEntity.ok(pets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        Optional<Pet> existingPet = petService.getPetById(id);
        if (existingPet.isPresent()) {
            Pet updatedPet = petService.updatePet(pet, id);
            if (updatedPet != null) {
                return ResponseEntity.ok("Mascota actualizada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallo al actualizar mascota");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
        if (petService.deletePet(id)) {
            return ResponseEntity.ok("Mascota eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falló al eliminar mascota");
        }
    }
    
    @GetMapping("/{id}")
public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
    Optional<Pet> pet = petService.getPetById(id);
    return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}
}
