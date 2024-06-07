<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<script src="components/navbarRedirect.js" type="text/javascript"></script>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    String welcomeMessage = (customer != null) ? "Bienvenido " + customer.getUsuario() : "Iniciar sesión";
    boolean isLoggedIn = (customer != null);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito de compra</title>
        <link href="resources/css/carrito.css" rel="stylesheet" type="text/css"/>
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
                    <li><a href="market.jsp">Tienda</a></li>
                    <li><a href="#">Servicios</a></li>
                    <li><a href="acercade.jsp">Acerca de</a></li>
                    <li><a href="Contactenos.jsp">Contacto</a></li>
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
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/img/inicio/CarroCompra.png" alt="">Carrito de compras</a>
                </div>
            </nav>
        </header>
        <div class="system-page" style="padding-top: 106.219px;" id="yui_3_17_2_1_1717545903367_444">
            <div id="sqs-cart-root">
                <div id="sqs-cart-container">
                    <div class="R2Lfol3tGyrBb2YQS6zR" id="yui_3_17_2_1_1717545903367_443">
                        <div class="i_wF9fo0lb5UREznwA1A">
                            <h2 aria-label="Carrito de compra" class="cart-title css-xxxyq5">Carrito de compra</h2>
                            <hr class="separator">
                        </div>
                        <div class="content-box">
                            <p aria-label="No tienes nada en tu carrito de compras." data-test="empty-message" class="empty-message css-1jp19h">No tienes nada en tu carrito de compras.</p>
                            <a aria-label="Seguir comprando" data-test="continue-shopping-link" class="E_E_ryEo_TmwszbGY79I H3OhlgGD9BlYw3JrmgAm sqs-editable-button sqs-button-element--secondary cart-continue-button" href="/">Seguir comprando</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="footer-container">
                <div class="footer-section">
                    <h3>Horario</h3>
                    <p>Lunes a viernes<br>De 6:30 a. m. a 11 p. m.</p>
                </div>
                <div class="footer-section">
                    <h3>Contacto</h3>
                    <p><a href="mailto:enchufate@gmail.com">enchufate@gmail.com</a><br>(555)555-5555</p>
                </div>
            </div>

        </footer>
    </body>
</html>
