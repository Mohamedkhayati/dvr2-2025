package com.med.marque.service;

import com.med.marque.dto.APIResponseDto;
import com.med.marque.dto.MarqueDto;
import com.med.marque.dto.SupplementDto;
import com.med.marque.entities.Marque;
import com.med.marque.repos.MarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MarqueServiceImpl implements MarqueService {

    private final MarqueRepository marqueRepository;
    private final APIClient apiClient; // prefer Feign client or WebClient wrapper

    @Override
    public APIResponseDto getMarqueById(Long id) {
        Optional<Marque> optionalMarque = marqueRepository.findById(id);
        if (optionalMarque.isEmpty()) {
            return null; // Controller can handle this as 404
        }

        Marque marque = optionalMarque.get();

        // Call another microservice to get Supplement info by name
        SupplementDto supplementDto = null;
        if (marque.getSuppnom() != null && !marque.getSuppnom().isEmpty()) {
            supplementDto = apiClient.getSupByCode(marque.getSuppnom());
        }

        // Build MarqueDto
        MarqueDto marqueDto = new MarqueDto(
                marque.getId(),
                marque.getNom(),
                marque.getPays(),
                marque.getSuppnom(),
                (supplementDto != null) ? supplementDto.getPrix() : null
        );

        // Combine into APIResponseDto
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setMarqueDto(marqueDto);
        apiResponseDto.setSupplementDto(supplementDto);

        return apiResponseDto;
    }
}
