<%-- 
    Document   : contactenos
    Created on : 3 jun. 2024, 11:47:08 p. m.
    Author     : aldom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contactenos</title>
        <link href="resources/css/Contactenos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <main>
            <section class="contact">
                <div class="contact-info">
                    <h2>Contáctanos</h2>
                    <p>Cuenta sobre qué pueden consultar y qué deben esperar después de que se comuniquen contigo. No olvides elegir una opción de almacenamiento para los mensajes que recibas.</p>
                    <p>correo electronico@ejemplo.com<br>(555) 555-5555<br>Calle de ejemplo, 123<br>Nueva York, NY 12345</p>
                </div>
                <form accion="<%= request.getContextPath()%>/cntContact" id="contactForm">

                    <label for="nombre">Nombre (obligatorio)</label>
                    <input type="text" id="nombre" name="nombre" value="${nombre}" required>

                    <label for="apellidos">Apellidos</label>
                    <input type="text" id="apellidos" name="apellidos" value="${apellidos}">

                    <label for="email">Correo electrónico (obligatorio)</label>
                    <input type="email" id="email" name="email" value="${correo}" required>

                    <label for="mensaje">Mensaje (obligatorio)</label>
                    <textarea id="mensaje" name="mensaje" value="${mensaje}" required></textarea>

                    <button type="submit" value="enviar" name="accion">Enviar</button>
                </form>
                <div id="formResponse"></div>
            </section>
        </main>
        <script src="resources/scrip/contactenos.js" type="text/javascript"></script>

    </body>
</html>
