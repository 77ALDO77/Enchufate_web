<%@ page import="java.util.List" %>
<%@ page import="modelo.dto.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Carrito de compra</title>
    <link href="${pageContext.request.contextPath}/resources/css/carrito.css" rel="stylesheet" type="text/css"/>
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
    <div class="system-page">
        <div id="sqs-cart-root">
            <div id="sqs-cart-container">
                <div class="R2Lfol3tGyrBb2YQS6zR">
                    <div class="i_wF9fo0lb5UREznwA1A">
                        <h2 aria-label="Carrito de compra" class="cart-title css-xxxyq5">Carrito de compra</h2>
                        <hr class="separator">
                    </div>
                    <div class="content-box">
                        <%-- Mostrar mensaje de error si el carrito está vacío --%>
                        <c:if test="${not empty errorMessage}">
                            <div class="error-message">${errorMessage}</div>
                        </c:if>
                        <div class="cart-products-container">
                            <c:forEach var="producto" items="${productosEnCarrito}">
                                <div class="cart-product">
                                    <img src="${pageContext.request.contextPath}/resources/img/inicio/${producto.imagen}" alt="${producto.nombre}" class="cart-product-image">
                                    <div class="cart-product-details">
                                        <div class="cart-product-name">${producto.nombre}</div>
                                        <div class="cart-product-price">S/.${producto.precio}</div>
                                        <div class="cart-product-quantity">Cantidad: ${producto.cantidad}</div>
                                    </div>
                                </div>
                                <hr class="product-separator">
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="components/pie.jsp"/>
</body>
</html>

