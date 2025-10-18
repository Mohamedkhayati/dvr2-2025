package com.med.marque.service;

import com.med.marque.dto.SupplementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(url = "http://localhost:8080", value = "SUPPLEMENT")
public interface APIClient {
    @GetMapping("api/supplements/{supplement-code}")
    SupplementDto getSupByCode(@PathVariable("supplement-code")
                               String supplementCode
    );
}