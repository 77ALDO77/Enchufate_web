<%-- 
    Document   : locales
    Created on : 3 jun. 2024, 22:46:58
    Author     : juand
--%>

<%@page import="conexion.ConectaBD"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="ServLets.cntLocales"%>
<%@page import="modelo.dao.LocalesDAO"%>
<%@page import="modelo.dto.Locales"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="components/navbarRedirect.js" type="text/javascript"></script>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    String welcomeMessage = (customer != null) ? "Bienvenido " + customer.getUsuario() : "Iniciar sesión";
    boolean isLoggedIn = (customer != null);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Locales</title>
        <link href="resources/css/estiloLocales.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="#">Tienda</a></li>
                    <li><a href="#">Servicios</a></li>
                    <li><a href="acercade.jsp">Acerca de</a></li>
                    <li><a href="#">Contacto</a></li>
                    <li><a href="locales.jsp">Locales</a></li>
                </ul>
                <div class="logo"><a href="Inicio.jsp">ENCHUFATE</a></div>
                <div class="auth-cart">
                    <button class="btnnavbar btn-color" style="padding: 0px 16px; cursor: pointer; height: 36px">
                    <%= isLoggedIn ? welcomeMessage : "<a href='login.jsp'>" + welcomeMessage + "</a>"%>
                </button>

                <% if (isLoggedIn) { %>
                <button class="btnnavbar btn-color" style="padding: 0px 16px; cursor: pointer; height: 36px">
                    <a href="logout">Cerrar sesión</a>
                </button>
                <% }%>   
                    <a href="Carrito.jsp"><img src="${pageContext.request.contextPath}/resources/img/inicio/CarroCompra.png" alt="">Carrito de compras</a>
                </div>
            </nav>
        </header>
        <%
            Boolean redireccionado = (Boolean) session.getAttribute("redireccionado");
            if (redireccionado == null || !redireccionado) {
                response.sendRedirect(request.getContextPath() + "/cntLocales?accion=locales");

                session.setAttribute("redireccionado", true);
            }
        %>
        <main class="principal">
        <div class="locales">
            <div class="titulo"><h1>Locales</h1></div>
            <hr><br>
            <div style="padding: 0px 0px 0px 12px"><h2>Aquí podrás encontrar todos los locales disponibles de Enchufate</h2></div>
            <div class="contenedor">
                <table class="tabla">
                    <caption>Lista de Locales</caption>
                    <thead class="cabeza">
                        <tr>
                            <td>Código</td><td>Nombre</td><td>Direccion</td><td>Telefono</td>
                        </tr>    
                    </thead>
                    <tbody class="cuerpo">
                        <c:forEach var="local" items="${lista}">
                            <tr class="contenido">
                                <td>${local.codLocal}</td>
                                <td>${local.nombre}</td>
                                <td>${local.direccion}</td>
                                <td>${local.telefono}</td>
                            </tr>
                        </c:forEach>           
                    </tbody>
                    <tfoot class="pie">
                        <tr>
                            <td colspan="4"><b>Enchufate</b></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        </main>
        <footer>
            <div class="footer-container">
                <div class="footer-section">
                    <h3>Horario</h3>
                    <p>Lunes a viernes<br>De 6:30 a. m. a 11 p. m.</p>
                </div>
                <div class="footer-section">
                    <h3>Contacto</h3>
                    <p><a href="mailto:enchufate@gmail.com">enchufate@gmail.com</a><br>(555) 555-5555</p>
                </div>
            </div>
            
        </footer>
    </body>
</html>