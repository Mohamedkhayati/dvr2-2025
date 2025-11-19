package com.med.marque.service;
import com.med.marque.dto.SupplementDto;
import org.springframework.stereotype.Component;

@Component

public class SuppFallback implements APIClient {
    @Override
    public SupplementDto getSupByCode(String departmentCode) {
        return null;
    }

}
