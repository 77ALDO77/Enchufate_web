<%-- 
    Document   : Inicio
    Created on : 3 jun. 2024, 6:04:13 p. m.
    Author     : aldom
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
//inicio caca 
<!DOCTYPE html>
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
                    <li><a href="#">Tienda</a></li>
                    <li><a href="#">Servicios</a></li>
                    <li><a href="#">Acerca de</a></li>
                    <li><a href="#">Contacto</a></li>
                </ul>
                <div class="logo">ENCHUFATE</div>
                <div class="auth-cart">
                    <a href="#">Iniciar sesión</a>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/img/inicio/CarroCompra.png" alt="">Carrito de compras</a>
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
                    <h3>Ubicación</h3>
                    <p>Av Arenales 1737 local 5 20 Centro Comercial Arenales en Lince</p>
                </div>
                <div class="footer-section">
                    <h3>Horario</h3>
                    <p>Lunes a viernes<br>De 6:30 a. m. a 11 p. m.</p>
                </div>
                <div class="footer-section">
                    <h3>Contacto</h3>
                    <p><a href="mailto:enchufate@gmail.com">enchufate@gmail.com</a><br>(555) 555-5555</p>
                </div>
            </div>
            <div class="footer-image">
                <img src="${pageContext.request.contextPath}/resources/img/footer-image.png" alt="">
            </div>
        </footer>
    </body>
</html>