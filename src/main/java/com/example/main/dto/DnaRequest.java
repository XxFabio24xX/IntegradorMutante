package com.example.main.dto;


import lombok.Getter;
import lombok.Setter;
import com.example.main.validators.ValidDna;

@Getter
@Setter
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
