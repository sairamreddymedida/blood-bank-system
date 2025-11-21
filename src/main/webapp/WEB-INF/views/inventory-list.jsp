<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Inventory - Blood Bank</title>
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
                <h2>Blood Inventory</h2>
                <a href="${pageContext.request.contextPath}/inventory?action=edit" class="btn btn-primary">+ Add Blood Unit</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Blood Type</th>
                            <th>Quantity (ml)</th>
                            <th>Expiry Date</th>
                            <th>Status</th>
                            <th>Date Added</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty inventoryList}">
                                <tr>
                                    <td colspan="7" style="text-align: center; padding: 40px;">No inventory items found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="item" items="${inventoryList}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td><strong>${item.bloodType}</strong></td>
                                        <td>${item.quantity} ml</td>
                                        <td>${item.expiryDateString}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.expired}">
                                                    <span class="status-badge status-expired">Expired</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="status-badge status-${item.status}">${item.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${item.dateAdded}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/inventory?action=edit&id=${item.id}" 
                                               class="btn btn-edit">Edit</a>
                                            <a href="${pageContext.request.contextPath}/inventory?action=delete&id=${item.id}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('Are you sure you want to delete this inventory item?')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</body>
</html>

