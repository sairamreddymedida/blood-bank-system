<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donors - Blood Bank</title>
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
                <h2>Donors</h2>
                <a href="${pageContext.request.contextPath}/donors?action=edit" class="btn btn-primary">+ Add Donor</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Blood Type</th>
                            <th>Age</th>
                            <th>Last Donation</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty donorList}">
                                <tr>
                                    <td colspan="8" style="text-align: center; padding: 40px;">No donors found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="donor" items="${donorList}">
                                    <tr>
                                        <td>${donor.id}</td>
                                        <td><strong>${donor.name}</strong></td>
                                        <td>${donor.email}</td>
                                        <td>${donor.phone}</td>
                                        <td><strong>${donor.bloodType}</strong></td>
                                        <td>${donor.age}</td>
                                        <td>${donor.lastDonationDateString != null ? donor.lastDonationDateString : 'Never'}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/donors?action=edit&id=${donor.id}" 
                                               class="btn btn-edit">Edit</a>
                                            <a href="${pageContext.request.contextPath}/donors?action=delete&id=${donor.id}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('Are you sure you want to delete this donor?')">Delete</a>
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

