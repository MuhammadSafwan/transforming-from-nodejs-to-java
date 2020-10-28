package com.defect.reporting.assignment.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Safwan
 */

@Component
public class DefectInputValidator {

    private static final String MACHINE_DEFECT_ID = "[0-9]+";

    public boolean isValidInputId(Integer machineOrDefectId) {
        return Pattern.matches(MACHINE_DEFECT_ID, Integer.toString(machineOrDefectId));
    }
}
