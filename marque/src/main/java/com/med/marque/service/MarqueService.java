package com.med.marque.service;

import com.med.marque.dto.APIResponseDto;

public interface MarqueService {
    APIResponseDto getMarqueById(Long id);
}
