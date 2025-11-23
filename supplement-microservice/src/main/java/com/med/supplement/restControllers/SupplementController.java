package com.med.supplement.restControllers;

import com.med.supplement.config.Configuration;
import com.med.supplement.dto.SupplementDto;
import com.med.supplement.service.SupplementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/supplements")
public class SupplementController {

    private final SupplementService supplementService;
    private final Configuration configuration;

    // Fallback if Config Server is unreachable
    @Value("${author.name:Unknown Author}")
    private String fallbackName;

    @Value("${author.email:unknown@example.com}")
    private String fallbackEmail;

    @Value("${build.version:unknown}")
    private String buildVersion;

    // Constructor injection (recommended)
    public SupplementController(SupplementService supplementService,
                                Configuration configuration) {
        this.supplementService = supplementService;
        this.configuration = configuration;
    }

    // Get supplement by name
    @GetMapping("/{suppNom}")
    public ResponseEntity<SupplementDto> getSupplementByNom(@PathVariable("suppNom") String suppNom) {
        SupplementDto dto = supplementService.getSupplementByNom(suppNom);
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplement not found: " + suppNom);
        }
        return ResponseEntity.ok(dto);
    }

    // Microservice version
    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok(buildVersion);
    }

    // Author info – now safe even if Config Server is down
    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        try {
            String name = (configuration.getName() != null) ? configuration.getName() : fallbackName;
            String email = (configuration.getEmail() != null) ? configuration.getEmail() : fallbackEmail;
            String authorInfo = name + " <" + email + ">";
            return ResponseEntity.ok(authorInfo);
        } catch (Exception e) {
            // Log the real exception in production
            String fallback = fallbackName + " <" + fallbackEmail + ">";
            return ResponseEntity.ok(fallback); // never let this endpoint crash the service
        }
    }

    // Optional: global exception handler (put in a separate class)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedError(Exception ex) {
        // log ex properly in real project
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }
}