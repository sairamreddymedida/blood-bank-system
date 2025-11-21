package com.bloodbank.controller;

import com.bloodbank.model.BloodRequest;
import com.bloodbank.service.BloodBankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requests")
public class RequestServlet extends HttpServlet {
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
                BloodRequest bloodRequest = service.getRequestById(id);
                request.setAttribute("request", bloodRequest);
            }
            request.getRequestDispatcher("/WEB-INF/views/request-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                service.deleteRequest(id);
            }
            response.sendRedirect(request.getContextPath() + "/requests");
        } else {
            // List all requests
            request.setAttribute("requestList", service.getAllRequests());
            request.getRequestDispatcher("/WEB-INF/views/request-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("create".equals(action) || "update".equals(action)) {
            BloodRequest bloodRequest = new BloodRequest();
            
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                bloodRequest.setId(Long.parseLong(idParam));
            }
            
            bloodRequest.setPatientName(request.getParameter("patientName"));
            bloodRequest.setBloodType(request.getParameter("bloodType"));
            bloodRequest.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            bloodRequest.setHospital(request.getParameter("hospital"));
            bloodRequest.setUrgency(request.getParameter("urgency"));
            bloodRequest.setStatus(request.getParameter("status"));
            
            service.saveRequest(bloodRequest);
            response.sendRedirect(request.getContextPath() + "/requests");
        } else {
            // Show form for new request
            request.getRequestDispatcher("/WEB-INF/views/request-form.jsp").forward(request, response);
        }
    }
}

