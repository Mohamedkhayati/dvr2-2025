package com.med.marque.repos;

import com.med.marque.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
    // You can remove findByNom if not used anymore
}
