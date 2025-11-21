package com.bloodbank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BloodRequest {
    private Long id;
    private String patientName;
    private String bloodType;
    private Integer quantity;
    private String hospital;
    private String urgency;
    private String status;
    private String dateRequested;
    private String dateFulfilled;

    public BloodRequest() {
        this.dateRequested = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.status = "pending";
        this.urgency = "normal";
    }

    public BloodRequest(Long id, String patientName, String bloodType, Integer quantity, String hospital, String urgency, String status) {
        this.id = id;
        this.patientName = patientName;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.hospital = hospital;
        this.urgency = urgency != null ? urgency : "normal";
        this.status = status != null ? status : "pending";
        this.dateRequested = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if ("fulfilled".equals(status) && dateFulfilled == null) {
            this.dateFulfilled = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getDateFulfilled() {
        return dateFulfilled;
    }

    public void setDateFulfilled(String dateFulfilled) {
        this.dateFulfilled = dateFulfilled;
    }
}

