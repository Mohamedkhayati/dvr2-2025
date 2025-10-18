package com.med.supplement.restControllers;

import com.med.supplement.dto.SupplementDto;
import com.med.supplement.service.SupplementService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/supplements")
@AllArgsConstructor
public class SupplementController {

    private final SupplementService supplementService;

    @GetMapping("{Suppnom}")
    public ResponseEntity<SupplementDto> getSupplementByNom(@PathVariable("Suppnom") String Suppnom) {
        return new ResponseEntity<>(supplementService.getSupplementByNom(Suppnom), HttpStatus.OK);
    }
}
