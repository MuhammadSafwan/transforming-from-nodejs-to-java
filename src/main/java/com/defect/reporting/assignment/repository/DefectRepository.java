package com.defect.reporting.assignment.repository;

import com.defect.reporting.assignment.model.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Safwan
 */
public interface DefectRepository extends JpaRepository<Defect, Integer> {
}
