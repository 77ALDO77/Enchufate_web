<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enchufate - Menú</title>
    <link href="${pageContext.request.contextPath}/resources/css/market.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/inicio.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
    <jsp:include page="components/encabezado.jsp"/>
    <%
        List<modelo.dto.Producto> productos = (List<modelo.dto.Producto>) request.getAttribute("listaProductos");
        if (productos == null) {
            response.sendRedirect(request.getContextPath() + "/productos");
            return; 
        }
    %>
    <header>
        <h1>Servicios Adicionales</h1>
        <p>Consulta los servicios adicionales que ofrecemos</p>
    </header>
    <main>
        <section class="menu-section">
            <h2>Productos</h2>
            <div class="menu-items">
                <c:forEach var="producto" items="${listaProductos}">
                    <div class="menu-item">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/${producto.imagen}" alt="${producto.nombre}">
                        <p><strong>${producto.nombre}</strong></p>
                        <p>${producto.descripcion}</p>
                        <p>Precio: $${producto.precio}</p>
                    </div>
                </c:forEach>
                <c:if test="${empty listaProductos}">
                    <p>No hay productos disponibles.</p>
                </c:if>
            </div>
        </section>
    </main>
    <jsp:include page="components/pie.jsp"/>
</body>
</html>

