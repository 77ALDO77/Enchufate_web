<%-- 
    Document   : acercade
    Created on : 4 jun. 2024, 18:00:07
    Author     : juand
--%>

<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/acercade.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/encabezado.jsp"/>
        <div id="Quienessomos" class="fade-in">
            <div class="subtitulo">
                <h2><b>¿QUIÉNES SOMOS?</b></h2>
            </div>
            <hr>
            <br>
            Bienvenidos a nuestro cibercafé, un espacio donde la tecnología y la 
            comodidad se fusionan para brindarnos la mejor experiencia digital. 
            Aquí, contamos con equipos de última generación y una conexión a 
            internet de alta velocidad que nos permite navegar, trabajar, 
            estudiar y disfrutar de nuestros videojuegos favoritos sin 
            interrupciones. Nuestro ambiente acogedor, con zonas de trabajo y 
            descanso, es ideal para relajarnos mientras tomamos un delicioso 
            café o un refrigerio. Además, organizamos eventos y talleres para 
            mantenernos actualizados en el mundo digital. Ven y comparte con 
            nosotros la pasión por la tecnología en un lugar diseñado para 
            conectarnos y crecer juntos.
        </div>
        <div class="vm">
            <div id="Vision" class="fade-in">
                <div class="subtitulo">
                    <h2><b>VISIÓN</b></h2>
                </div>
                <hr>
                <br>
                Nos visualizamos como el principal destino para aquellos que buscan 
                optimizar su tiempo y mejorar su calidad de vida en la ciudad de 
                Lima. A través de la continua innovación y adaptación, aspiramos a 
                ser reconocidos como líderes en la industria de los Ciber-cafés, 
                freciendo soluciones que excedan las expectativas de nuestros 
                clientes.
            </div>
            <br>
            <div id="Mision" class="fade-in">
                <div class="subtitulo">
                    <h2><b>MISIÓN</b></h2>
                </div>
                <hr>
                <br>
                Enchufate se compromete a proporcionar un espacio innovador y 
                acogedor que combine la funcionalidad de un espacio de trabajo con 
                la comodidad de un ambiente relajado. Nos esforzamos por satisfacer 
                las necesidades de estudiantes y profesionales urbanos al ofrecer 
                acceso a internet, entretenimiento y servicios adicionales en un 
                entorno seguro y eficiente.
            </div>
        </div>
        <jsp:include page="components/pie.jsp"/>
    </body>
</html>
