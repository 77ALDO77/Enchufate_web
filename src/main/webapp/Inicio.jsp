<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<script src="components/navbarRedirect.js" type="text/javascript"></script>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    String welcomeMessage = (customer != null) ? "Bienvenido " + customer.getUsuario() : "Iniciar sesión";
    boolean isLoggedIn = (customer != null);
%>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Enchufate</title>
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
                    <a href="Carrito.jsp"><img src="${pageContext.request.contextPath}/resources/img/inicio/CarroCompra.png" alt="">Carrito de compras</a>
                </div>
            </nav>
        </header>
        <main>
            <div class="hero">
                <h1>ENCHUFATE</h1>
                <p>Tu Tiempo, Tu Espacio, Tu Conexión</p>
                <a href="#" class="btn">Más información</a>
            </div>

            <!-- Nueva Sección de Cubículos -->
            <section class="cubicles">
                <h2>Servicio de Cubículo</h2>
                <div class="cubicle-container">
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/Normal.png" alt="Cubículo General">
                        <h3>Cubículo General</h3>
                        <p>S/. 5 x hora</p>
                    </div>
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/VIP.png" alt="Cubículo VIP">
                        <h3>Cubículo VIP</h3>
                        <p>S/. 8 x hora</p>
                    </div>
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/VIPUltra.png" alt="Cubículo ULTRA VIP">
                        <h3>Cubículo ULTRA VIP</h3>
                        <p>S/. 12 x hora</p>
                    </div>
                </div>
                <a href="#" class="btn">Tienda</a>
            </section>
        </main>
        <footer>
            <div class="footer-container">
                <div class="footer-section">
                    <h3>Horario</h3>
                    <p>Lunes a viernes<br>De 6:30 a. m. a 11 p. m.</p>
                    <p><a href="Reclamos.jsp">Libro de Reclamaciones</a></p>
                </div>
                <div class="footer-section">
                    <h3>Contacto</h3>
                    <p><a href="mailto:enchufate@gmail.com">enchufate@gmail.com</a><br>(555) 555-5555</p>
                </div>
            </div>
            
        </footer>
    </body>
</html>