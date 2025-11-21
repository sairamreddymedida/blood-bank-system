package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.service.BloodBankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/donors")
public class DonorServlet extends HttpServlet {
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
                Donor donor = service.getDonorById(id);
                request.setAttribute("donor", donor);
            }
            request.getRequestDispatcher("/WEB-INF/views/donor-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                service.deleteDonor(id);
            }
            response.sendRedirect(request.getContextPath() + "/donors");
        } else {
            // List all donors
            request.setAttribute("donorList", service.getAllDonors());
            request.getRequestDispatcher("/WEB-INF/views/donor-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("create".equals(action) || "update".equals(action)) {
            Donor donor = new Donor();
            
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                donor.setId(Long.parseLong(idParam));
            }
            
            donor.setName(request.getParameter("name"));
            donor.setEmail(request.getParameter("email"));
            donor.setPhone(request.getParameter("phone"));
            donor.setBloodType(request.getParameter("bloodType"));
            donor.setAge(Integer.parseInt(request.getParameter("age")));
            donor.setLastDonationDateString(request.getParameter("lastDonationDate"));
            donor.setAddress(request.getParameter("address"));
            
            service.saveDonor(donor);
            response.sendRedirect(request.getContextPath() + "/donors");
        } else {
            // Show form for new donor
            request.getRequestDispatcher("/WEB-INF/views/donor-form.jsp").forward(request, response);
        }
    }
}

