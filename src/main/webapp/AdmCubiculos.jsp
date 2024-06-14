<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/adminDasboard.css" rel="stylesheet" type="text/css"/>      
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Cubiculos</title>
    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content">
            <div class="container">
                <main class="main-content">
                    <header class="header">
                        <h1>Administración de Cubículos</h1>
                    </header>
                    <section class="dashboard">
                        <div class="stats">
                            <div action="<%= request.getContextPath()%>/cntCubiculos" class="card">
                                <h3>Cubículos Activos</h3>
                                <p>${cubiculosActivos}</p>
                            </div>
                            <div class="card">
                                <h3>Reservas Hoy</h3>
                                <p>${reservasHoy}</p>
                            </div>
                        </div>
                        <div class="details">
                            <h2>Detalles de Cubículos</h2>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nivel</th>
                                        <th>Estado</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="cubiculo" items="${cubiculos}">
                                    <tr>
                                        <td>${cubiculo.codCubiculo}</td>
                                        <td>${cubiculo.nivel}</td>
                                        <td>${cubiculo.estado}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </section>
                </main>
            </div>
    </body>
</html>