package com.bloodbank.controller;

import com.bloodbank.model.BloodInventory;
import com.bloodbank.service.BloodBankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private BloodBankService service;

    @Override
    public void init() throws ServletException {
        service = new BloodBankService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                BloodInventory item = service.getInventoryById(id);
                request.setAttribute("inventory", item);
            }
            request.getRequestDispatcher("/WEB-INF/views/inventory-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                service.deleteInventory(id);
            }
            response.sendRedirect(request.getContextPath() + "/inventory");
        } else {
            // List all inventory
            request.setAttribute("inventoryList", service.getAllInventory());
            request.getRequestDispatcher("/WEB-INF/views/inventory-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("create".equals(action) || "update".equals(action)) {
            BloodInventory inventory = new BloodInventory();
            
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                inventory.setId(Long.parseLong(idParam));
            }
            
            inventory.setBloodType(request.getParameter("bloodType"));
            inventory.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            inventory.setExpiryDateString(request.getParameter("expiryDate"));
            inventory.setStatus(request.getParameter("status"));
            
            String donorIdParam = request.getParameter("donorId");
            if (donorIdParam != null && !donorIdParam.isEmpty()) {
                inventory.setDonorId(Long.parseLong(donorIdParam));
            }
            
            service.saveInventory(inventory);
            response.sendRedirect(request.getContextPath() + "/inventory");
        } else {
            // Show form for new item
            request.getRequestDispatcher("/WEB-INF/views/inventory-form.jsp").forward(request, response);
        }
    }
}

