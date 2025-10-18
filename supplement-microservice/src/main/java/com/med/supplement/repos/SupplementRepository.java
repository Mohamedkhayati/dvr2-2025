package com.med.supplement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.med.supplement.entities.Supplement;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    // Must match the entity field name exactly
    Supplement findBySuppnom(String suppnom);
}
