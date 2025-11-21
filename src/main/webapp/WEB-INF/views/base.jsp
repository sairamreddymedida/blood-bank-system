<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Bank Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🩸 Blood Bank Management System</h1>
            <nav class="tabs">
                <a href="${pageContext.request.contextPath}/inventory" 
                   class="tab-btn ${param.activeTab == 'inventory' ? 'active' : ''}">Blood Inventory</a>
                <a href="${pageContext.request.contextPath}/donors" 
                   class="tab-btn ${param.activeTab == 'donors' ? 'active' : ''}">Donors</a>
                <a href="${pageContext.request.contextPath}/requests" 
                   class="tab-btn ${param.activeTab == 'requests' ? 'active' : ''}">Blood Requests</a>
            </nav>
        </header>
        
        <main>
            <jsp:include page="${contentPage}" />
        </main>
    </div>
</body>
</html>

