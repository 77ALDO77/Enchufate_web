<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Enchufate</title>
        <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/reclamos.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/encabezado.jsp"/>
        <main class="feedback-form">
            <div>Reclamos</div>
            <div class="mlps-header">
                <div class="mlps-h-b"></div>
                <div style="padding: 0px 0px 0px 12px">
                    ¡Nos encantaría escuchar tus sugerencias! ¿Tienes alguna idea para mejorar nuestro servicio? ¡Déjanos tus comentarios aquí!
                </div>
            </div>
            <div style="display: flex; place-content: center; margin: 50px 0px;">
                <div class="suggestion-form">
                    <form action="<%= request.getContextPath()%>/cntReclamos" method="post" class="formulario" id="formClaims">
                        <label for="ID">DNI:</label><br>
                        <input type="text" id="ID" name="ID" required pattern="\d{8}" title="El DNI debe contener 8 dígitos"><br>
                        <label for="name">Nombre:</label><br>
                        <input type="text" id="name" name="name" required><br>
                        <label for="fecha_reclamo">Fecha:</label><br>
                        <input type="text" id="fecha_reclamo" name="fecha_reclamo" required><br>
                        <label for="email">Correo electrónico:</label><br>
                        <input type="email" id="email" name="email" required><br>
                        <label for="Asunto">Asunto:</label><br>
                        <input type="text" id="Asunto" name="Asunto" required><br>
                        <label for="ContenidoReclamo">Reclamo:</label><br>
                        <textarea id="ContenidoReclamo" name="ContenidoReclamo" rows="4" cols="42" required></textarea><br>
                        <input type="submit" value="Enviar" name="accion">
                    </form>
                </div>
            </div>
        </main>
        <jsp:include page="components/pie.jsp"/>
    </body>
    <---hola>
</html>