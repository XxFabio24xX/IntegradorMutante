package com.example.main.repositories;


import com.example.main.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {
    Optional<Dna> findByDna(String dnaSequence);  // Metodo de busqueda

    long countByIsMutant(boolean isMutant);  // Metodo de conteo
}