package com.defect.reporting.assignment.service;

import com.defect.reporting.assignment.exception.DefectNotFoundException;
import com.defect.reporting.assignment.exception.InvalidInputException;
import com.defect.reporting.assignment.exception.MachineNotFoundException;
import com.defect.reporting.assignment.exception.WorkerNotFoundException;
import com.defect.reporting.assignment.model.Machine;
import com.defect.reporting.assignment.model.Worker;
import com.defect.reporting.assignment.repository.DefectRepository;
import com.defect.reporting.assignment.model.Defect;
import com.defect.reporting.assignment.repository.MachineRepository;
import com.defect.reporting.assignment.repository.WorkerRegistryRepository;
import com.defect.reporting.assignment.validator.DefectInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Safwan
 */

@Service
public class DefectService {

    @Autowired
    private DefectInputValidator defectInputValidator;

    @Autowired
    private DefectRepository defectRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private WorkerRegistryRepository workerRegistryRepository;

    private static final String SUCCESS_MESSAGE_DEFECT_CREATED = "Successfully set the defect.";
    private static final String SUCCESS_MESSAGE_DEFECT_UPDATED = "Successfully updated the status of defect.";
    private final String ERROR_MESSAGE_DEFECT_NOT_FOUND = "No defect was found with the id: ";
    private final String ERROR_MESSAGE_WORKER_NOT_FOUND = "No worker was found with the personal number: ";
    private final String ERROR_MESSAGE_MACHINE_NOT_FOUND = "No machine was found with the id: :";

    public Defect getDefect(int defectId) {
        validateInputId(defectId);
        Optional<Defect> defect = defectRepository.findById(defectId);
        if(defect.isPresent()) {
            return defect.get();
        }
        else {
            throw new DefectNotFoundException(ERROR_MESSAGE_DEFECT_NOT_FOUND + defectId);
        }
    }

    public String createDefect(int machineId, int workerId, int defectStatus, String description) {
        validateInputId(machineId);
        validateInputId(workerId);

        Optional<Machine> machine = machineRepository.findById(machineId);
        if(machine.isPresent()) {
            Machine existingMachine = machine.get();
            Optional<Worker> worker = workerRegistryRepository.findById(workerId);
            if(worker.isPresent()) {
                Defect defect = new Defect(worker.get(), description, defectStatus, new Date());
                existingMachine.setDefect(defect);
                machineRepository.save(existingMachine);
                return SUCCESS_MESSAGE_DEFECT_CREATED;
            } else {
                throw new WorkerNotFoundException(ERROR_MESSAGE_WORKER_NOT_FOUND + workerId);
            }
        } else {
            throw new MachineNotFoundException(ERROR_MESSAGE_MACHINE_NOT_FOUND + machineId);
        }
    }

    public String updateDefectStatus(int defectId, int defectStatus){
        validateInputId(defectId);
        Defect defect = getDefect(defectId);
        defect.setDefectStatus(defectStatus);
        defectRepository.save(defect);
        return SUCCESS_MESSAGE_DEFECT_UPDATED;
    }

    public List<Defect> getAllDefects() {
        return defectRepository.findAll();
    }

    private void validateInputId(int inputId) {
        if(!defectInputValidator.isValidInputId(inputId)) {
            throw new InvalidInputException("Something is wrong with the id " + inputId + ". Please provide numbers only without spaces");
        }
    }
}
