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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Locales</title>
    </head>
    <body>
        <%
            Boolean redireccionado = (Boolean) session.getAttribute("redireccionado");
            if (redireccionado == null || !redireccionado) {
                response.sendRedirect(request.getContextPath() + "/cntLocales?accion=locales");

                session.setAttribute("redireccionado", true);
            }
        %>
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
                            <td colspan="3"><b>Enchufate</b></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>

    </body>
</html>
