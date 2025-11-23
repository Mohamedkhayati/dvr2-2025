package com.med.marque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    private SupplementDto supplementDto;
    private  MarqueDto marqueDto;

}
