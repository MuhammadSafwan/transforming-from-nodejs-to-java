package com.defect.reporting.assignment.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Safwan
 */

@Entity
@Table(name = "defect")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int defectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_number")
    private Worker worker;

    @Column(name = "description")
    private String description;

    @Column(name = "defect_status")
    private int defectStatus;

    @Column(name = "defect_time")
    private Date defectTime;

    public Defect() {
    }

    public Defect(Worker personalNumber, String description, int defectStatus, Date defectTime) {
        this.worker = personalNumber;
        this.description = description;
        this.defectStatus = defectStatus;
        this.defectTime = defectTime;
    }

    public int getDefectId() {
        return defectId;
    }

    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDefectStatus() {
        return defectStatus;
    }

    public void setDefectStatus(int defectStatus) {
        this.defectStatus = defectStatus;
    }

    public Date getDefectTime() {
        return defectTime;
    }

    public void setDefectTime(Date defectTime) {
        this.defectTime = defectTime;
    }
}
