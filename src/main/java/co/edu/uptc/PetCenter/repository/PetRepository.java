package co.edu.uptc.PetCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uptc.PetCenter.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
   
}
