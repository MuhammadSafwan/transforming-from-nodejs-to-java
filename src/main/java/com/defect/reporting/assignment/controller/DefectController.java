package com.defect.reporting.assignment.controller;

import com.defect.reporting.assignment.model.Defect;
import com.defect.reporting.assignment.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Safwan
 */

@RestController
@RequestMapping("/defect")
public class DefectController {

    @Autowired
    private DefectService defectService;

    @PatchMapping("/updateDefectStatus")
    public String updateDefectStatus(@RequestParam(value = "defectId") int defectId,
                                     @RequestParam(value = "defectStatus") int defectStatus) {
        return defectService.updateDefectStatus(defectId, defectStatus);
    }

    @GetMapping("/getDefect/{defectId}")
    public Defect getDefect(@PathVariable int defectId) {
        return defectService.getDefect(defectId);
    }

    @PostMapping("/createDefect")
    public String setDefect(@RequestParam(value = "machine_id") int machineId,
                            @RequestParam(value = "personal_number") int personalNumber,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "defectStatus") int defectStatus) {
        return defectService.createDefect(machineId, personalNumber, defectStatus, description);
    }

    @GetMapping("/getAll")
    public List<Defect> getAllDefects() {
        return defectService.getAllDefects();
    }

}
