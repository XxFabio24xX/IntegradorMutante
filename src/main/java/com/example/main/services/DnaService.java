package com.example.main.services;

import com.example.main.entities.Dna;
import com.example.main.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class DnaService {

    private static final int SEQUENCE_LENGTH = 4;
    private final DnaRepository dnaRepository;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    // Analiza el ADN y determina si es mutante o no
    public boolean analizarDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificamos si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            // Si el ADN ya fue analizado, retornamos el resultado
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);

        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;

        return IntStream.range(0, n)
                .anyMatch(i -> verificarFilas(dna[i]) ||
                        verificarColumnas(dna, i, n) ||
                        verificarDiagonalPrincipal(dna, n, i) ||
                        verificarDiagonalSecundaria(dna, n, i));
    }

    // Verifica si una secuencia en una fila tiene letras consecutivas iguales
    private static boolean verificarFilas(String fila) {
        return IntStream.range(0, fila.length() - SEQUENCE_LENGTH + 1)
                .anyMatch(i -> IntStream.range(i, i + SEQUENCE_LENGTH)
                        .mapToObj(fila::charAt)
                        .distinct()
                        .count() == 1);
    }

    // Verifica si una secuencia en una columna tiene letras consecutivas iguales
    private static boolean verificarColumnas(String[] dna, int columna, int tamano) {
        return IntStream.range(0, tamano - SEQUENCE_LENGTH + 1)
                .anyMatch(i -> IntStream.range(i, i + SEQUENCE_LENGTH)
                        .mapToObj(fila -> dna[fila].charAt(columna))
                        .distinct()
                        .count() == 1);
    }

    // Verifica si una secuencia en la diagonal principal tiene letras consecutivas iguales
    private static boolean verificarDiagonalPrincipal(String[] dna, int tamano, int inicio) {
        if (inicio > tamano - SEQUENCE_LENGTH) return false;
        return IntStream.range(0, SEQUENCE_LENGTH)
                .mapToObj(i -> dna[inicio + i].charAt(inicio + i))
                .distinct()
                .count() == 1;
    }

    // Verifica si una secuencia en la diagonal secundaria tiene letras consecutivas iguales
    private static boolean verificarDiagonalSecundaria(String[] dna, int tamano, int inicio) {
        if (inicio > tamano - SEQUENCE_LENGTH) return false;
        return IntStream.range(0, SEQUENCE_LENGTH)
                .mapToObj(i -> dna[inicio + i].charAt(tamano - 1 - (inicio + i)))
                .distinct()
                .count() == 1;
    }

    // Metodo auxiliar para verificar si todos los caracteres en una secuencia son iguales
    private static boolean tieneCaracteresIguales(String secuencia) {
        return secuencia.chars().distinct().count() == 1;
    }
}