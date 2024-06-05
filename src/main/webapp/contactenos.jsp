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
                <form action="<%= request.getContextPath()%>/cntContac" method="post" class="formulario" id="formContacto">
                    <div class="formBox">
                        <div class="row50">
                            <div class="inputBox">
                                <span>Nombre Completo</span>
                                <input type="text" name="nombre" id="nombre"
                                       placeholder="Carla Soto" value="${nombre}">
                            </div>
                            <div class="inputBox">
                                <span>Apellido Completo</span>
                                <input type="text" name="apellido" id="apellido"
                                       placeholder="Carla Soto" value="${apellido}">
                            </div>
                            <div class="row50">
                                <div class="inputBox">
                                    <span>Correo Electrónico</span>
                                    <input type="text" name="correo" id="correo"
                                           placeholder="carlas@gmail.com" value="${correo}">
                                </div>
                            </div>
                            <div class="row100">
                                <div class="inputBox">
                                    <span>Mensaje</span>
                                    <textarea name="mensaje" id="mensaje"
                                              placeholder="Escriba su mensaje aquí...">${mensaje}</textarea>
                                </div>
                            </div>
                            <div class="row100">
                                <div class="inputBox">
                                    <input type="submit"  style="border-radius: 5px;" value="Enviar" name="accion">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div id="formResponse"></div>
            </section>
        </main>
        <script src="resources/scripts/contactenos.js" type="text/javascript"></script>
    </body>
</html>
