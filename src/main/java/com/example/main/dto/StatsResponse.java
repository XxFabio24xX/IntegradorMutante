package com.example.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    private long count_mutant_dna;

    @JsonProperty("count_human_dna")
    private long count_human_dna;

    private double ratio;
}
