package com.med.supplement.service;

import com.med.supplement.dto.SupplementDto;
import com.med.supplement.entities.Supplement;
import com.med.supplement.repos.SupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplementServiceImpl implements SupplementService {

    @Autowired
    private SupplementRepository supplementRepository;

    @Override
    public SupplementDto getSupplementByNom(String Suppnom) {
        Supplement sup = supplementRepository.findBySuppnom(Suppnom);
        return new SupplementDto(sup.getId(), sup.getSuppnom(), sup.getPrix());
    }
}
