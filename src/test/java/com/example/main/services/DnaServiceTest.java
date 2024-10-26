package com.example.main.services;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DnaServiceTest {


    @Test
    public void testFilas() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(DnaService.isMutant(dna));
    }

    @Test
    public void testColumnas() {
        String[] dna = {
                "ATGCGA",
                "AAGTGC",
                "ATTAGT",
                "AGAAGG",
                "TCCCTA",
                "TCACTG"
        };
        assertTrue(DnaService.isMutant(dna));
    }

    @Test
    public void testMainDiagonales() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTGTGT",
                "AGATAG",
                "CCCTTA",
                "TCACTG"
        };
        assertTrue(DnaService.isMutant(dna));
    }

    @Test
    public void testSubDiagonalesIzquierda() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(DnaService.isMutant(dna));
    }

    @Test
    public void testSubDiagonalesDerecha() {
        String[] dna = {
                "CTGCGA",
                "AAGTGC",
                "TTGTGT",
                "GGATGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(DnaService.isMutant(dna));
    }

    @Test
    public void testNoMutant() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(DnaService.isMutant(dna));
    }

}

