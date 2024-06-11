<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
            response.sendRedirect(request.getContextPath() + "/cntProducto");
            return;
        }
        System.out.println("Número de productos en JSP: " + productos.size()); // Depuración

        // Agrupar productos por categoría
        Map<String, List<modelo.dto.Producto>> productosPorCategoria = new HashMap<>();
        for (modelo.dto.Producto producto : productos) {
            productosPorCategoria.computeIfAbsent(producto.getNombreCategoria(), k -> new ArrayList<>()).add(producto);
        }
        request.setAttribute("productosPorCategoria", productosPorCategoria); // Asegúrate de pasar el mapa a la JSP
    %>
    <header>
        <h1>Servicios Adicionales</h1>
        <p>Consulta los servicios adicionales que ofrecemos</p>
    </header>
    <main>
        <section class="menu-section">
            <h2>Productos</h2>
            <c:forEach var="categoria" items="${productosPorCategoria.keySet()}">
                <h3>${categoria}</h3>
                <div class="menu-items">
                    <c:forEach var="producto" items="${productosPorCategoria[categoria]}">
                        <div class="menu-item">
                            <img src="${pageContext.request.contextPath}/resources/img/inicio/${producto.imagen}" alt="${producto.nombre}">
                            <p><strong>${producto.nombre}</strong></p>
                            <p>${producto.descripcion}</p>
                            <p>Precio: $${producto.precio}</p>
                            <form action="${pageContext.request.contextPath}/agregarAlCarrito" method="post">
                                <input type="hidden" name="codproducto" value="${producto.codproducto}" />
                                <button type="submit">Agregar al Carrito</button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            <c:if test="${productos.isEmpty()}">
                <p>No hay productos disponibles.</p>
            </c:if>
        </section>
    </main>
    <jsp:include page="components/pie.jsp"/>
</body>
</html>