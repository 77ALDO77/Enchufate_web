<%-- 
    Document   : createa-account
    Created on : 24 may. 2024, 12:34:21
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear cuenta</title>
    <link href="resources/css/create-account.css" rel="stylesheet" type="text/css"/>
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- jQuery UI -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <style>
        .alert {
            padding: 15px;
            background-color: #4CAF50; /* Green */
            color: white;
            margin-bottom: 15px;
            border-radius: 4px;
            text-align: center;
        }
    </style>
    <script>
        $(function() {
            $("#fecha_nacimiento").datepicker({
                dateFormat: 'yy-mm-dd', // Formato de fecha
                changeMonth: true, // Permitir cambio de mes
                changeYear: true, // Permitir cambio de año
                yearRange: "-100:+0", // Rango de años permitidos
                maxDate: "0", // Fecha máxima seleccionable (hoy)
            });
        });
    </script>
</head>
<body>
    <div class="create-account">
        <% String success = request.getParameter("success"); %>
        <% if (success != null && success.equals("true")) { %>
            <div class="alert">
                Cuenta creada con éxito
            </div>
        <% } %>
        <h1>Crear cuenta</h1>
        <form action="<%= request.getContextPath()%>/cntCustomer" method="post" class="formulario" id="formCreateAccount">
            <div class="">
                <div class="create-account-box">
                    <input type="text" placeholder="Nombre" data-test="create-account-first-name" name="nombre" >
                </div>
            </div>
            <div class="account-last-name">
                <div class="lastname-p">
                    <div class="create-account-box">
                        <input type="text" placeholder="Apellido Paterno" data-test="create-account-last-name1" name="apepaterno">
                    </div>
                </div>
                <div class="">
                    <div class="create-account-box">
                        <input type="text" placeholder="Apellido Materno" data-test="create-account-last-name2" name="apematerno">
                    </div>
                </div>
            </div>
            <div class="account-dni-birthdate">
                <div class="account-dni-p">
                    <div class="create-account-box">
                        <input type="dni" placeholder="DNI" data-test="create-account-dni" name="dni">
                    </div>
                </div>
                <div class="">
                    <div class="create-account-box">
                        <input type="text" placeholder="Fecha de Nacimiento" data-test="create-account-birthdate" id="fecha_nacimiento" name="fechanacimiento">
                    </div>
                </div>
            </div>
            <div class="">
                <div class="create-account-box">
                    <input type="username" placeholder="Nombre de Usuario" data-test="create-account-username" name="usuario">
                </div>
            </div>
            <div class="">
                <div class="create-account-box">
                    <input type="email" placeholder="Correo electrónico" data-test="create-account-email" name="correo">
                </div>
            </div>
            <div class="">
                <div class="create-account-box">
                    <input type="password" placeholder="Crear contraseña" data-test="create-account-password" name="contrasena">
                </div>
            </div>
            <%--<div class="">
                <div class="create-account-box">
                    <input type="password" placeholder="Vuelve a escribir la contraseña" data-test="create-account-confirm-password" value="">
                </div>
            </div>--%>
            
            <input class="btn-creat-account" data-test="create-account-create-button" type="submit"  style="border-radius: 5px;" value="Crear Cuenta" name="accion">
        </form>
        <footer>
            <a href="login.jsp">
                <span>¿Ya tienes una cuenta? Inicia sesión</span>
            </a>
        </footer>
    </div>
</body>
</html>
