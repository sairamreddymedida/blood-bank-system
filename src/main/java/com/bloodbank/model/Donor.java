package com.bloodbank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Donor {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String bloodType;
    private Integer age;
    private LocalDate lastDonationDate;
    private String address;
    private String dateRegistered;

    public Donor() {
        this.dateRegistered = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public Donor(Long id, String name, String email, String phone, String bloodType, Integer age, LocalDate lastDonationDate, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodType = bloodType;
        this.age = age;
        this.lastDonationDate = lastDonationDate;
        this.address = address;
        this.dateRegistered = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public String getLastDonationDateString() {
        return lastDonationDate != null ? lastDonationDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

    public void setLastDonationDateString(String lastDonationDate) {
        if (lastDonationDate != null && !lastDonationDate.isEmpty()) {
            this.lastDonationDate = LocalDate.parse(lastDonationDate);
        } else {
            this.lastDonationDate = null;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}

