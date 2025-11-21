<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${inventory != null ? 'Edit' : 'Add'} Blood Unit - Blood Bank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🩸 Blood Bank Management System</h1>
            <nav class="tabs">
                <a href="${pageContext.request.contextPath}/inventory" class="tab-btn active">Blood Inventory</a>
                <a href="${pageContext.request.contextPath}/donors" class="tab-btn">Donors</a>
                <a href="${pageContext.request.contextPath}/requests" class="tab-btn">Blood Requests</a>
            </nav>
        </header>

        <section class="tab-content active">
            <div class="section-header">
                <h2>${inventory != null ? 'Edit' : 'Add'} Blood Unit</h2>
                <a href="${pageContext.request.contextPath}/inventory" class="btn btn-secondary">Back to List</a>
            </div>

            <div class="form-container">
                <form action="${pageContext.request.contextPath}/inventory" method="post">
                    <input type="hidden" name="action" value="${inventory != null ? 'update' : 'create'}">
                    <c:if test="${inventory != null}">
                        <input type="hidden" name="id" value="${inventory.id}">
                    </c:if>

                    <div class="form-group">
                        <label for="bloodType">Blood Type *</label>
                        <select id="bloodType" name="bloodType" required>
                            <option value="">Select Blood Type</option>
                            <option value="A+" ${inventory != null && inventory.bloodType == 'A+' ? 'selected' : ''}>A+</option>
                            <option value="A-" ${inventory != null && inventory.bloodType == 'A-' ? 'selected' : ''}>A-</option>
                            <option value="B+" ${inventory != null && inventory.bloodType == 'B+' ? 'selected' : ''}>B+</option>
                            <option value="B-" ${inventory != null && inventory.bloodType == 'B-' ? 'selected' : ''}>B-</option>
                            <option value="AB+" ${inventory != null && inventory.bloodType == 'AB+' ? 'selected' : ''}>AB+</option>
                            <option value="AB-" ${inventory != null && inventory.bloodType == 'AB-' ? 'selected' : ''}>AB-</option>
                            <option value="O+" ${inventory != null && inventory.bloodType == 'O+' ? 'selected' : ''}>O+</option>
                            <option value="O-" ${inventory != null && inventory.bloodType == 'O-' ? 'selected' : ''}>O-</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="quantity">Quantity (ml) *</label>
                        <input type="number" id="quantity" name="quantity" 
                               value="${inventory != null ? inventory.quantity : ''}" 
                               required min="1">
                    </div>

                    <div class="form-group">
                        <label for="expiryDate">Expiry Date *</label>
                        <input type="date" id="expiryDate" name="expiryDate" 
                               value="${inventory != null ? inventory.expiryDateString : ''}" 
                               required>
                    </div>

                    <div class="form-group">
                        <label for="status">Status</label>
                        <select id="status" name="status">
                            <option value="available" ${inventory != null && inventory.status == 'available' ? 'selected' : ''}>Available</option>
                            <option value="reserved" ${inventory != null && inventory.status == 'reserved' ? 'selected' : ''}>Reserved</option>
                            <option value="expired" ${inventory != null && inventory.status == 'expired' ? 'selected' : ''}>Expired</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="donorId">Donor ID (Optional)</label>
                        <input type="number" id="donorId" name="donorId" 
                               value="${inventory != null && inventory.donorId != null ? inventory.donorId : ''}">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="${pageContext.request.contextPath}/inventory" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </section>
    </div>
</body>
</html>

