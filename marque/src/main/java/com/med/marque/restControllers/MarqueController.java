package com.med.marque.restControllers;

import com.med.marque.config.Configuration;
import com.med.marque.dto.APIResponseDto;
import com.med.marque.service.MarqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marques")
@AllArgsConstructor
public class MarqueController {
    @Autowired
    Configuration configuration;

    private final MarqueService marqueService;

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponseDto> getMarqueById(@PathVariable("id") Long id) {
        APIResponseDto response = marqueService.getMarqueById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        String authorInfo = configuration.getName() + " <" + configuration.getEmail() + ">";
        return ResponseEntity.ok(authorInfo);
    }
}
