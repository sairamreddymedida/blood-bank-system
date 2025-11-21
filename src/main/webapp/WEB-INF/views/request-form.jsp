<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${request != null ? 'Edit' : 'New'} Blood Request - Blood Bank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🩸 Blood Bank Management System</h1>
            <nav class="tabs">
                <a href="${pageContext.request.contextPath}/inventory" class="tab-btn">Blood Inventory</a>
                <a href="${pageContext.request.contextPath}/donors" class="tab-btn">Donors</a>
                <a href="${pageContext.request.contextPath}/requests" class="tab-btn active">Blood Requests</a>
            </nav>
        </header>

        <section class="tab-content active">
            <div class="section-header">
                <h2>${request != null ? 'Edit' : 'New'} Blood Request</h2>
                <a href="${pageContext.request.contextPath}/requests" class="btn btn-secondary">Back to List</a>
            </div>

            <div class="form-container">
                <form action="${pageContext.request.contextPath}/requests" method="post">
                    <input type="hidden" name="action" value="${request != null ? 'update' : 'create'}">
                    <c:if test="${request != null}">
                        <input type="hidden" name="id" value="${request.id}">
                    </c:if>

                    <div class="form-group">
                        <label for="patientName">Patient Name *</label>
                        <input type="text" id="patientName" name="patientName" 
                               value="${request != null ? request.patientName : ''}" 
                               required>
                    </div>

                    <div class="form-group">
                        <label for="bloodType">Blood Type *</label>
                        <select id="bloodType" name="bloodType" required>
                            <option value="">Select Blood Type</option>
                            <option value="A+" ${request != null && request.bloodType == 'A+' ? 'selected' : ''}>A+</option>
                            <option value="A-" ${request != null && request.bloodType == 'A-' ? 'selected' : ''}>A-</option>
                            <option value="B+" ${request != null && request.bloodType == 'B+' ? 'selected' : ''}>B+</option>
                            <option value="B-" ${request != null && request.bloodType == 'B-' ? 'selected' : ''}>B-</option>
                            <option value="AB+" ${request != null && request.bloodType == 'AB+' ? 'selected' : ''}>AB+</option>
                            <option value="AB-" ${request != null && request.bloodType == 'AB-' ? 'selected' : ''}>AB-</option>
                            <option value="O+" ${request != null && request.bloodType == 'O+' ? 'selected' : ''}>O+</option>
                            <option value="O-" ${request != null && request.bloodType == 'O-' ? 'selected' : ''}>O-</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="quantity">Quantity (ml) *</label>
                        <input type="number" id="quantity" name="quantity" 
                               value="${request != null ? request.quantity : ''}" 
                               required min="1">
                    </div>

                    <div class="form-group">
                        <label for="hospital">Hospital</label>
                        <input type="text" id="hospital" name="hospital" 
                               value="${request != null ? request.hospital : ''}">
                    </div>

                    <div class="form-group">
                        <label for="urgency">Urgency</label>
                        <select id="urgency" name="urgency">
                            <option value="normal" ${request != null && request.urgency == 'normal' ? 'selected' : ''}>Normal</option>
                            <option value="urgent" ${request != null && request.urgency == 'urgent' ? 'selected' : ''}>Urgent</option>
                            <option value="critical" ${request != null && request.urgency == 'critical' ? 'selected' : ''}>Critical</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="status">Status</label>
                        <select id="status" name="status">
                            <option value="pending" ${request != null && request.status == 'pending' ? 'selected' : ''}>Pending</option>
                            <option value="fulfilled" ${request != null && request.status == 'fulfilled' ? 'selected' : ''}>Fulfilled</option>
                            <option value="cancelled" ${request != null && request.status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="${pageContext.request.contextPath}/requests" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </section>
    </div>
</body>
</html>

