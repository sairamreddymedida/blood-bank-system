<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${donor != null ? 'Edit' : 'Add'} Donor - Blood Bank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🩸 Blood Bank Management System</h1>
            <nav class="tabs">
                <a href="${pageContext.request.contextPath}/inventory" class="tab-btn">Blood Inventory</a>
                <a href="${pageContext.request.contextPath}/donors" class="tab-btn active">Donors</a>
                <a href="${pageContext.request.contextPath}/requests" class="tab-btn">Blood Requests</a>
            </nav>
        </header>

        <section class="tab-content active">
            <div class="section-header">
                <h2>${donor != null ? 'Edit' : 'Add'} Donor</h2>
                <a href="${pageContext.request.contextPath}/donors" class="btn btn-secondary">Back to List</a>
            </div>

            <div class="form-container">
                <form action="${pageContext.request.contextPath}/donors" method="post">
                    <input type="hidden" name="action" value="${donor != null ? 'update' : 'create'}">
                    <c:if test="${donor != null}">
                        <input type="hidden" name="id" value="${donor.id}">
                    </c:if>

                    <div class="form-group">
                        <label for="name">Name *</label>
                        <input type="text" id="name" name="name" 
                               value="${donor != null ? donor.name : ''}" 
                               required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" 
                               value="${donor != null ? donor.email : ''}" 
                               required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone *</label>
                        <input type="tel" id="phone" name="phone" 
                               value="${donor != null ? donor.phone : ''}" 
                               required>
                    </div>

                    <div class="form-group">
                        <label for="bloodType">Blood Type *</label>
                        <select id="bloodType" name="bloodType" required>
                            <option value="">Select Blood Type</option>
                            <option value="A+" ${donor != null && donor.bloodType == 'A+' ? 'selected' : ''}>A+</option>
                            <option value="A-" ${donor != null && donor.bloodType == 'A-' ? 'selected' : ''}>A-</option>
                            <option value="B+" ${donor != null && donor.bloodType == 'B+' ? 'selected' : ''}>B+</option>
                            <option value="B-" ${donor != null && donor.bloodType == 'B-' ? 'selected' : ''}>B-</option>
                            <option value="AB+" ${donor != null && donor.bloodType == 'AB+' ? 'selected' : ''}>AB+</option>
                            <option value="AB-" ${donor != null && donor.bloodType == 'AB-' ? 'selected' : ''}>AB-</option>
                            <option value="O+" ${donor != null && donor.bloodType == 'O+' ? 'selected' : ''}>O+</option>
                            <option value="O-" ${donor != null && donor.bloodType == 'O-' ? 'selected' : ''}>O-</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="age">Age *</label>
                        <input type="number" id="age" name="age" 
                               value="${donor != null ? donor.age : ''}" 
                               required min="18" max="65">
                    </div>

                    <div class="form-group">
                        <label for="lastDonationDate">Last Donation Date</label>
                        <input type="date" id="lastDonationDate" name="lastDonationDate" 
                               value="${donor != null && donor.lastDonationDateString != null ? donor.lastDonationDateString : ''}">
                    </div>

                    <div class="form-group">
                        <label for="address">Address</label>
                        <textarea id="address" name="address" rows="3">${donor != null ? donor.address : ''}</textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="${pageContext.request.contextPath}/donors" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </section>
    </div>
</body>
</html>

