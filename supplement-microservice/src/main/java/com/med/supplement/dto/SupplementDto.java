package com.med.supplement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplementDto {
    private Long id;
    private String Suppnom;
    private double prix;
}
