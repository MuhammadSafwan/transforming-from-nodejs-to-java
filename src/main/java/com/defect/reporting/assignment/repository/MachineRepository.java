package com.defect.reporting.assignment.repository;

import com.defect.reporting.assignment.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Safwan
 */
public interface MachineRepository extends JpaRepository<Machine, Integer> {
}
