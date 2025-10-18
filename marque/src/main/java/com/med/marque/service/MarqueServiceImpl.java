package com.med.marque.service;

import com.med.marque.dto.APIResponseDto;
import com.med.marque.dto.MarqueDto;
import com.med.marque.dto.SupplementDto;
import com.med.marque.entities.Marque;
import com.med.marque.repos.MarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MarqueServiceImpl implements MarqueService {

    private final MarqueRepository marqueRepository;
    private final WebClient webClient;

    @Override
    public APIResponseDto getMarqueById(Long id) {
        Optional<Marque> optionalMarque = marqueRepository.findById(id);
        if (optionalMarque.isEmpty()) {
            return null; // Will return 404 from controller
        }

        Marque marque = optionalMarque.get();

        SupplementDto supplementDto = null;
        if (marque.getSuppnom() != null && !marque.getSuppnom().isEmpty()) {
            supplementDto = webClient.get()
                    .uri("http://localhost:8080/api/supplements/" + marque.getSuppnom())
                    .retrieve()
                    .bodyToMono(SupplementDto.class)
                    .block();
        }

        MarqueDto marqueDto = new MarqueDto(
                marque.getId(),
                marque.getNom(),
                marque.getPays(),
                marque.getSuppnom()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setMarqueDto(marqueDto);
        apiResponseDto.setSupplementDto(supplementDto);

        return apiResponseDto;
    }
}
