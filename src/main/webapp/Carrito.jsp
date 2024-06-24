<%@ page import="java.util.List" %>
<%@ page import="modelo.dto.Producto" %>
<%@ page import="modelo.dao.CarritoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    // Obtener el codCliente de la sesión del usuario
    Integer codCliente = (Integer) session.getAttribute("codCliente");

    if (codCliente == null) {
        response.sendRedirect("login.jsp"); // Redirigir al login si no está autenticado
        return;
    }

    CarritoDAO carritoDAO = new CarritoDAO();
    List<Producto> productosEnCarrito = carritoDAO.getProductosEnCarrito(codCliente);

    // Procesar acciones de incremento o decremento de cantidad
    String codProductoParam = request.getParameter("codProducto");
    String action = request.getParameter("action");

    if (codProductoParam != null && action != null) {
        int codProducto = Integer.parseInt(codProductoParam);
        if ("increase".equals(action) || ("decrease".equals(action) && productosEnCarrito.stream().anyMatch(p -> p.getCodproducto() == codProducto))) {
            carritoDAO.actualizarCantidad(codCliente, codProducto, action);
        } else if ("delete".equals(action)) {
            carritoDAO.eliminarProductoDelCarrito(codCliente, codProducto);
        }
        // Refrescar la lista de productos en el carrito después de la actualización o eliminación
        productosEnCarrito = carritoDAO.getProductosEnCarrito(codCliente);
    }

    // Establecer la lista de productos en el carrito como un atributo de solicitud
    request.setAttribute("productosEnCarrito", productosEnCarrito);
%>
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
        <div class="cart-title-container">
            <h2 aria-label="Carrito de compra" class="cart-title css-xxxyq5">Carrito de compra</h2>
        </div>
        <div class="cart-content-container">
            <div class="R2Lfol3tGyrBb2YQS6zR">
                <div class="i_wF9fo0lb5UREznwA1A">
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
                                    <div class="cart-product-quantity">
                                        Cantidad: ${producto.cantidad}
                                        <div class="quantity-buttons">
                                            <form action="Carrito.jsp" method="post">
                                                <input type="hidden" name="codProducto" value="${producto.codproducto}">
                                                <input type="hidden" name="action" value="increase">
                                                <button type="submit" class="sqs-button-element--secondary">+</button>
                                            </form>
                                            <form action="Carrito.jsp" method="post">
                                                <input type="hidden" name="codProducto" value="${producto.codproducto}">
                                                <input type="hidden" name="action" value="decrease">
                                                <button type="submit" class="sqs-button-element--secondary">-</button>
                                            </form>
                                            <form action="Carrito.jsp" method="post">
                                                <input type="hidden" name="codProducto" value="${producto.codproducto}">
                                                <input type="hidden" name="action" value="delete">
                                                <button type="submit" class="sqs-button-element--secondary">Eliminar</button>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="cart-product-total">Total: S/.${producto.precio * producto.cantidad}</div>
                                </div>
                            </div>
                            <hr class="product-separator">
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="cart-summary content-box">
                <div class="cart-grand-total">
                    <c:set var="grandTotal" value="0.0" />
                    <c:forEach var="producto" items="${productosEnCarrito}">
                        <c:set var="productTotal" value="${producto.precio * producto.cantidad}" />
                        <c:set var="grandTotal" value="${grandTotal + productTotal}" />
                    </c:forEach>
                    Total de productos: S/.<c:out value="${grandTotal}" />
                </div>
                <div class="payment-methods">
                    <img src="${pageContext.request.contextPath}/resources/img/payments/payment_mastercard.png" alt="Método de pago 1" class="payment-method-image">
                    <img src="${pageContext.request.contextPath}/resources/img/payments/payment_pagoefectivo.png" alt="Método de pago 2" class="payment-method-image">
                    <img src="${pageContext.request.contextPath}/resources/img/payments/payment_visa.png" alt="Método de pago 3" class="payment-method-image">
                </div>
                <button class="sqs-button-element--secondary buy-button">Comprar</button>
            </div>
        </div>
    </div>
    <jsp:include page="components/pie.jsp"/>
</body>
</html>
