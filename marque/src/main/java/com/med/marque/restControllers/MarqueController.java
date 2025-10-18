package com.med.marque.restControllers;

import com.med.marque.dto.APIResponseDto;
import com.med.marque.service.MarqueService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marques")
@AllArgsConstructor
public class MarqueController {

    private final MarqueService marqueService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponseDto> getMarqueById(@PathVariable("id") Long id) {
        APIResponseDto response = marqueService.getMarqueById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
