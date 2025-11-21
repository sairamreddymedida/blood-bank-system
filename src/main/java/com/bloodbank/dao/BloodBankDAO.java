package com.bloodbank.dao;

import com.bloodbank.model.BloodInventory;
import com.bloodbank.model.BloodRequest;
import com.bloodbank.model.Donor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BloodBankDAO {
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = DATA_DIR + File.separator + "bloodbank.json";
    private static final ObjectMapper objectMapper;
    
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    private static final AtomicLong inventoryIdGenerator = new AtomicLong(System.currentTimeMillis());
    private static final AtomicLong donorIdGenerator = new AtomicLong(System.currentTimeMillis());
    private static final AtomicLong requestIdGenerator = new AtomicLong(System.currentTimeMillis());

    static {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                BloodBankData initialData = new BloodBankData();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, initialData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ========== INVENTORY OPERATIONS ==========
    
    public List<BloodInventory> getAllInventory() {
        try {
            BloodBankData data = readData();
            return data.getInventory() != null ? data.getInventory() : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public BloodInventory getInventoryById(Long id) {
        return getAllInventory().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveInventory(BloodInventory inventory) {
        try {
            BloodBankData data = readData();
            if (data.getInventory() == null) {
                data.setInventory(new ArrayList<>());
            }
            
            if (inventory.getId() == null) {
                inventory.setId(inventoryIdGenerator.incrementAndGet());
                data.getInventory().add(inventory);
            } else {
                List<BloodInventory> inventoryList = data.getInventory();
                for (int i = 0; i < inventoryList.size(); i++) {
                    if (inventoryList.get(i).getId().equals(inventory.getId())) {
                        inventoryList.set(i, inventory);
                        break;
                    }
                }
            }
            writeData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInventory(Long id) {
        try {
            BloodBankData data = readData();
            if (data.getInventory() != null) {
                data.setInventory(data.getInventory().stream()
                        .filter(item -> !item.getId().equals(id))
                        .collect(Collectors.toList()));
                writeData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ========== DONOR OPERATIONS ==========
    
    public List<Donor> getAllDonors() {
        try {
            BloodBankData data = readData();
            return data.getDonors() != null ? data.getDonors() : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Donor getDonorById(Long id) {
        return getAllDonors().stream()
                .filter(donor -> donor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveDonor(Donor donor) {
        try {
            BloodBankData data = readData();
            if (data.getDonors() == null) {
                data.setDonors(new ArrayList<>());
            }
            
            if (donor.getId() == null) {
                donor.setId(donorIdGenerator.incrementAndGet());
                data.getDonors().add(donor);
            } else {
                List<Donor> donorList = data.getDonors();
                for (int i = 0; i < donorList.size(); i++) {
                    if (donorList.get(i).getId().equals(donor.getId())) {
                        donorList.set(i, donor);
                        break;
                    }
                }
            }
            writeData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDonor(Long id) {
        try {
            BloodBankData data = readData();
            if (data.getDonors() != null) {
                data.setDonors(data.getDonors().stream()
                        .filter(donor -> !donor.getId().equals(id))
                        .collect(Collectors.toList()));
                writeData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ========== REQUEST OPERATIONS ==========
    
    public List<BloodRequest> getAllRequests() {
        try {
            BloodBankData data = readData();
            return data.getRequests() != null ? data.getRequests() : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public BloodRequest getRequestById(Long id) {
        return getAllRequests().stream()
                .filter(request -> request.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void saveRequest(BloodRequest request) {
        try {
            BloodBankData data = readData();
            if (data.getRequests() == null) {
                data.setRequests(new ArrayList<>());
            }
            
            if (request.getId() == null) {
                request.setId(requestIdGenerator.incrementAndGet());
                data.getRequests().add(request);
            } else {
                List<BloodRequest> requestList = data.getRequests();
                for (int i = 0; i < requestList.size(); i++) {
                    if (requestList.get(i).getId().equals(request.getId())) {
                        requestList.set(i, request);
                        break;
                    }
                }
            }
            writeData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRequest(Long id) {
        try {
            BloodBankData data = readData();
            if (data.getRequests() != null) {
                data.setRequests(data.getRequests().stream()
                        .filter(request -> !request.getId().equals(id))
                        .collect(Collectors.toList()));
                writeData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ========== PRIVATE HELPER METHODS ==========
    
    private BloodBankData readData() throws IOException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new BloodBankData();
        }
        return objectMapper.readValue(file, BloodBankData.class);
    }

    private void writeData(BloodBankData data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATA_FILE), data);
    }

    // Inner class for JSON structure
    private static class BloodBankData {
        private List<BloodInventory> inventory;
        private List<Donor> donors;
        private List<BloodRequest> requests;

        public List<BloodInventory> getInventory() {
            return inventory;
        }

        public void setInventory(List<BloodInventory> inventory) {
            this.inventory = inventory;
        }

        public List<Donor> getDonors() {
            return donors;
        }

        public void setDonors(List<Donor> donors) {
            this.donors = donors;
        }

        public List<BloodRequest> getRequests() {
            return requests;
        }

        public void setRequests(List<BloodRequest> requests) {
            this.requests = requests;
        }
    }
}

