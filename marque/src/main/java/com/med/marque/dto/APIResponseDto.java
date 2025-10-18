package com.med.marque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    private  MarqueDto marqueDto;
    private SupplementDto supplementDto;
}
