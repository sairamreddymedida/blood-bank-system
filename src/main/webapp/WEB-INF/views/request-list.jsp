<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Requests - Blood Bank</title>
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
                <h2>Blood Requests</h2>
                <a href="${pageContext.request.contextPath}/requests?action=edit" class="btn btn-primary">+ New Request</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Patient Name</th>
                            <th>Blood Type</th>
                            <th>Quantity (ml)</th>
                            <th>Hospital</th>
                            <th>Urgency</th>
                            <th>Status</th>
                            <th>Date Requested</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty requestList}">
                                <tr>
                                    <td colspan="9" style="text-align: center; padding: 40px;">No requests found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="request" items="${requestList}">
                                    <tr>
                                        <td>${request.id}</td>
                                        <td><strong>${request.patientName}</strong></td>
                                        <td><strong>${request.bloodType}</strong></td>
                                        <td>${request.quantity} ml</td>
                                        <td>${request.hospital != null && !request.hospital.isEmpty() ? request.hospital : 'N/A'}</td>
                                        <td><span class="urgency-badge urgency-${request.urgency}">${request.urgency}</span></td>
                                        <td><span class="status-badge status-${request.status}">${request.status}</span></td>
                                        <td>${request.dateRequested}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/requests?action=edit&id=${request.id}" 
                                               class="btn btn-edit">Edit</a>
                                            <a href="${pageContext.request.contextPath}/requests?action=delete&id=${request.id}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('Are you sure you want to delete this request?')">Delete</a>
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

