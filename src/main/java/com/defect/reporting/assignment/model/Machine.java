package com.defect.reporting.assignment.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Safwan
 */

@Entity
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int machineId;

    @Column(name = "machine_type")
    private String machineType;

    @Column(name = "status")
    private int machineStatus;

    @Column(name = "workplace")
    private int workplace;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "machine_id")
    private Defect defect;

    public Machine() {
    }

    public Machine(String machineType, int machineStatus, int workplace) {
        this.machineType = machineType;
        this.machineStatus = machineStatus;
        this.workplace = workplace;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public int getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(int machineStatus) {
        this.machineStatus = machineStatus;
    }

    public int getWorkplace() {
        return workplace;
    }

    public void setWorkplace(int workplace) {
        this.workplace = workplace;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }
}
