package com.defect.reporting.assignment.service;

import com.defect.reporting.assignment.exception.DefectNotFoundException;
import com.defect.reporting.assignment.exception.InvalidInputException;
import com.defect.reporting.assignment.exception.MachineNotFoundException;
import com.defect.reporting.assignment.exception.WorkerNotFoundException;
import com.defect.reporting.assignment.model.Defect;
import com.defect.reporting.assignment.model.Machine;
import com.defect.reporting.assignment.model.Worker;
import com.defect.reporting.assignment.repository.DefectRepository;
import com.defect.reporting.assignment.repository.MachineRepository;
import com.defect.reporting.assignment.repository.WorkerRegistryRepository;
import com.defect.reporting.assignment.validator.DefectInputValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Safwan
 */

@RunWith(MockitoJUnitRunner.class)
public class DefectServiceTest {

    @Mock
    private DefectInputValidator defectInputValidator;

    @InjectMocks
    private DefectService defectService;

    @Mock
    private WorkerRegistryRepository workerRegistryRepository;

    @Mock
    private DefectRepository defectRepository;

    @Mock
    private MachineRepository machineRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery criteriaQuery;

    Worker worker;
    private Defect defect;
    private final String SUCCESS_MESSAGE_DEFECT_CREATED = "Successfully set the defect.";
    private final String SUCCESS_MESSAGE_DEFECT_UPDATED = "Successfully updated the status of defect.";
    private final String ERROR_MESSAGE_WORKER_NOT_FOUND = "No worker was found with the personal number: ";
    private final String ERROR_MESSAGE_MACHINE_NOT_FOUND = "No machine was found with the id: :";
    private Optional<Machine> machine;

    @Before
    public void setUp() throws Exception {
        worker = new Worker("TestName");
        defect = new Defect(worker, "System Failure", 1, new Date());
        machine = Optional.of(new Machine());
    }

    @Test
    public void shallReturnSuccessMessageForCreateDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(machineRepository.findById(1)).thenReturn(machine);
        when(workerRegistryRepository.findById(1)).thenReturn(Optional.ofNullable(worker));

        String result = defectService.createDefect(1, 1, 1, "System Failure");

        assertThat(result).isEqualTo(SUCCESS_MESSAGE_DEFECT_CREATED);
    }

    @Test(expected = DefectNotFoundException.class)
    public void shallReturnDefectNotFoundExceptionForGetDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(defectRepository.findById(1)).thenReturn(Optional.empty());
        defectService.getDefect(1);
    }

    @Test(expected = WorkerNotFoundException.class)
    public void shallReturnWorkerNotFoundExceptionForCreateDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(machineRepository.findById(1)).thenReturn(machine);
        when(workerRegistryRepository.findById(1)).thenReturn(Optional.empty());

        String result = defectService.createDefect(1, 1, 1, "System Failure");

        assertThat(result).isEqualTo(ERROR_MESSAGE_WORKER_NOT_FOUND);
    }

    @Test(expected = MachineNotFoundException.class)
    public void shallReturnMachineNotFoundExceptionForCreateDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(machineRepository.findById(1)).thenReturn(Optional.empty());

        String result = defectService.createDefect(1, 1, 1, "System Failure");

        assertThat(result).isEqualTo(ERROR_MESSAGE_MACHINE_NOT_FOUND);
    }

    @Test
    public void shallReturnSuccessMessageForGetDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(defectRepository.findById(anyInt())).thenReturn(Optional.ofNullable(defect));
        defectService.getDefect(1);
    }

    @Test(expected = DefectNotFoundException.class)
    public void shallReturnExceptionForGetDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        defectService.getDefect(1);
    }

    @Test
    public void shallReturnSuccessMessageForUpdatingDefect() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(true);
        when(defectRepository.findById(anyInt())).thenReturn(Optional.ofNullable(defect));
        String result = defectService.updateDefectStatus(1, 1);

        assertThat(result).isEqualTo(SUCCESS_MESSAGE_DEFECT_UPDATED);
    }

    @Test(expected = InvalidInputException.class)
    public void shallThrowInvalidDefectIdInputException() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(false);
        defectService.getDefect(1);
    }

    @Test(expected = InvalidInputException.class)
    public void shallThrowInvalidMachineIdInputException() {
        when(defectInputValidator.isValidInputId(anyInt())).thenReturn(false);
        defectService.createDefect(1, 1, 1, "Test");
    }

}
