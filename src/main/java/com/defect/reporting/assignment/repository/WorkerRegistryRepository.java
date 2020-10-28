package com.defect.reporting.assignment.repository;

import com.defect.reporting.assignment.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Safwan
 */
public interface WorkerRegistryRepository extends JpaRepository<Worker, Integer> {
}
