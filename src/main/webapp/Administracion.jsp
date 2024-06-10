<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="modelo.dto.Cubiculo" %>
<%@ page import="java.util.ArrayList" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administración de Cubículos</title>
    <link href="resources/css/Adm.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <h2>Ciber Café</h2>
            </div>
            <nav class="menu">
                <ul>
                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Cubículos</a></li>
                    <li><a href="#">Reservas</a></li>
                    <li><a href="#">Clientes</a></li>
                    <li><a href="#">Configuración</a></li>
                </ul>
            </nav>
        </aside>
        <main class="main-content">
            <header class="header">
                <h1>Administración de Cubículos</h1>
            </header>
            <section class="dashboard">
                <div class="stats">
                    <div class="card">
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
