package com.bloodbank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BloodInventory {
    private Long id;
    private String bloodType;
    private Integer quantity;
    private LocalDate expiryDate;
    private Long donorId;
    private String dateAdded;
    private String status;

    public BloodInventory() {
        this.dateAdded = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.status = "available";
    }

    public BloodInventory(Long id, String bloodType, Integer quantity, LocalDate expiryDate, Long donorId, String status) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.donorId = donorId;
        this.dateAdded = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.status = status != null ? status : "available";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDateString() {
        return expiryDate != null ? expiryDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

    public void setExpiryDateString(String expiryDate) {
        if (expiryDate != null && !expiryDate.isEmpty()) {
            this.expiryDate = LocalDate.parse(expiryDate);
        }
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}

