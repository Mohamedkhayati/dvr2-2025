package com.med.supplement.restControllers;

import com.med.supplement.config.Configuration;
import com.med.supplement.dto.SupplementDto;
import com.med.supplement.service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplements")
public class SupplementController {

    private final SupplementService supplementService;

    // build.version loaded from Config Server (with fallback)
    @Value("${build.version:unknown}")
    private String buildVersion;

    @Autowired
    private Configuration configuration;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    // 🔹 Get supplement by name
    @GetMapping("/{Suppnom}")
    public ResponseEntity<SupplementDto> getSupplementByNom(@PathVariable("Suppnom") String Suppnom) {
        return new ResponseEntity<>(supplementService.getSupplementByNom(Suppnom), HttpStatus.OK);
    }

    // 🔹 Check microservice version
    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok(buildVersion);
    }

    // 🔹 Retrieve author info (from Config Server)
    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        String authorInfo = configuration.getName() + " <" + configuration.getEmail() + ">";
        return ResponseEntity.ok(authorInfo);
    }
}
