<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<html>
<head>
    <title>Procesar Pago</title>
    <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
            background-size: cover;
        }
        .payment-result {
            margin: 50px auto;
            width: 400px;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            color: black;
            text-align: center;
        }
        .payment-result h2 {
            color: green;
        }
        .payment-result p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <jsp:include page="components/encabezado.jsp"/>
    <div class="payment-result">
        <% 
            String cardNumber = request.getParameter("card-number");
            String cardName = request.getParameter("card-name");
            String cardExpiry = request.getParameter("card-expiry");
            String cardCvv = request.getParameter("card-cvv");

            boolean pagoExitoso = true; 

            if (pagoExitoso) {
                out.println("<h2>Pago realizado con éxito</h2>");
                out.println("<p>Gracias por su compra, " + cardName + ".</p>");
            } else {
                out.println("<h2>Error en el pago</h2>");
                out.println("<p>Hubo un problema al procesar su pago. Por favor, intente nuevamente.</p>");
            }
        %>
    </div>
    <jsp:include page="components/pie.jsp"/>
</body>
</html>

