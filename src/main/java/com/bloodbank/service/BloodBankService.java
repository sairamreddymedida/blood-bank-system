package com.bloodbank.service;

import com.bloodbank.dao.BloodBankDAO;
import com.bloodbank.model.BloodInventory;
import com.bloodbank.model.BloodRequest;
import com.bloodbank.model.Donor;

import java.time.LocalDate;
import java.util.List;

public class BloodBankService {
    private BloodBankDAO dao;

    public BloodBankService() {
        this.dao = new BloodBankDAO();
    }

    // ========== INVENTORY SERVICE METHODS ==========
    
    public List<BloodInventory> getAllInventory() {
        return dao.getAllInventory();
    }

    public BloodInventory getInventoryById(Long id) {
        return dao.getInventoryById(id);
    }

    public void saveInventory(BloodInventory inventory) {
        dao.saveInventory(inventory);
    }

    public void deleteInventory(Long id) {
        dao.deleteInventory(id);
    }

    // ========== DONOR SERVICE METHODS ==========
    
    public List<Donor> getAllDonors() {
        return dao.getAllDonors();
    }

    public Donor getDonorById(Long id) {
        return dao.getDonorById(id);
    }

    public void saveDonor(Donor donor) {
        dao.saveDonor(donor);
    }

    public void deleteDonor(Long id) {
        dao.deleteDonor(id);
    }

    // ========== REQUEST SERVICE METHODS ==========
    
    public List<BloodRequest> getAllRequests() {
        return dao.getAllRequests();
    }

    public BloodRequest getRequestById(Long id) {
        return dao.getRequestById(id);
    }

    public void saveRequest(BloodRequest request) {
        dao.saveRequest(request);
    }

    public void deleteRequest(Long id) {
        dao.deleteRequest(id);
    }
}

