<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.dto.Customer" %>
<%
    // Obtener el cliente desde la sesión
    Customer customer = (Customer) session.getAttribute("customer");
    String userEmail = (customer != null) ? customer.getCorreo() : "";
%>
<html>
    <head>
        <title>Opciones de Pago</title>
        <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
                background-size: cover;
            }
            .payment-container {
                margin: 50px auto;
                width: 400px;
                padding: 20px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                color: black;
            }
            .payment-method {
                margin: 20px 0;
                display: flex;
                align-items: center;
                cursor: pointer;
            }
            .payment-method img {
                width: 50px;
                margin-right: 20px;
            }
            .payment-form, .qr-image {
                display: none;
                margin-top: 20px;
            }
            .qr-image img {
                width: 100%;
                max-width: 300px;
                margin: 0 auto;
                display: block;
            }
            .loading-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.5);
                justify-content: center;
                align-items: center;
            }
            .loading-overlay img, .loading-overlay video {
                max-width: 100px;
            }
            .payment-form label, .payment-form input {
                display: block;
                margin-bottom: 15px;
                width: 100%;
                padding: 10px;
                font-size: 16px;
                /* border: 1px solid #ccc;
                border-radius: 5px;*/
                box-sizing: border-box;
            }
            .payment-form input[type="text"]:focus {
                outline: none;
                border-color: #4CAF50;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            }
            .payment-form button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 18px;
            }
            .payment-form button:hover {
                background-color: #45a049;
            }
        </style>
        <script src="${pageContext.servletContext.contextPath}/resources/scrip/boleta_compra.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
        <script>
            var userEmail = '<%= userEmail%>';
        </script>

    </head>
    <body>
        <jsp:include page="components/encabezado.jsp"/>

        <div class="payment-container">
            <h2>Seleccione su método de pago</h2>
            <div class="payment-method" id="payment-visa">
                <img src="${pageContext.request.contextPath}/resources/img/payments/payment_visa.png" alt="Visa">
                <span>Pago con Visa</span>
            </div>
            <div class="payment-method" id="payment-mastercard">
                <img src="${pageContext.request.contextPath}/resources/img/payments/payment_mastercard.png" alt="Mastercard">
                <span>Pago con Mastercard</span>
            </div>
            <div class="payment-method" id="payment-efectivo">
                <img src="${pageContext.request.contextPath}/resources/img/payments/payment_yape.png" alt="Pago Efectivo">
                <span>Yape</span>
            </div>

            <div class="payment-form" id="card-form">
                <h3>Ingrese los datos de su tarjeta</h3>
                <form id="payment-form" action="procesarPago.jsp" method="post">
                    <label for="card-number">Número de tarjeta:</label>
                    <input type="text" id="card-number" name="card-number" placeholder="1234 5678 9012 3456" required>
                    <label for="card-name">Nombre en la tarjeta:</label>
                    <input type="text" id="card-name" name="card-name" placeholder="Nombre Apellido" required>
                    <label for="card-expiry">Fecha de expiración:</label>
                    <input type="text" id="card-expiry" name="card-expiry" placeholder="MM/AA" required>
                    <label for="card-cvv">CVV:</label>
                    <input type="text" id="card-cvv" name="card-cvv" placeholder="123" required>
                    <input type="hidden" id="user-email" value="<%= userEmail %>">
                    <button type="submit" onclick="sendMail();">Pagar</button>
                </form>
            </div>

            <div class="qr-image" id="qr-image">
                <h3>Escanea el código QR para pagar en efectivo</h3>
                <img src="${pageContext.request.contextPath}/resources/img/payments/qr_yape.jpg" alt="Código QR">
            </div>
        </div>

        <div class="loading-overlay" id="loading-overlay">
            <img src="${pageContext.request.contextPath}/resources/img/payments/carga.gif" alt="Cargando...">
        </div>

        <jsp:include page="components/pie.jsp"/>
        <script>
            document.getElementById('payment-visa').addEventListener('click', function () {
                document.getElementById('card-form').style.display = 'block';
                document.getElementById('qr-image').style.display = 'none';
            });

            document.getElementById('payment-mastercard').addEventListener('click', function () {
                document.getElementById('card-form').style.display = 'block';
                document.getElementById('qr-image').style.display = 'none';
            });

            document.getElementById('payment-efectivo').addEventListener('click', function () {
                document.getElementById('qr-image').style.display = 'block';
                document.getElementById('card-form').style.display = 'none';
            });

            document.getElementById('payment-form').addEventListener('submit', function (event) {
                document.getElementById('loading-overlay').style.display = 'flex';
            });
        </script>

        <script>
            var contextPath = '${pageContext.servletContext.contextPath}';
        </script>
        <script src="${pageContext.servletContext.contextPath}/resources/scrip/boleta_compra.js"></script> 
        <script>
            function sendMail() {
                (function () {
                    emailjs.init("7N5Thsnf8N9FFvZYA");
                })();
                var userEmail = document.getElementById('user-email').value; // Obtener el correo del usuario

                var params = {
                    to: userEmail,
                    subject: "Comprobante de compra enviado",
                    replyto: "noreply@gmail.com",
                    message: "Se le adjunta la boleta de la compra realizada con exito!",
                };

                var serviceID = "service_ebbh11w";
                var templateID = "template_9r0o1js";

                emailjs.send(serviceID, templateID, params)
                        .then(res => {
                            alter("Email Sent Successfully!");
                        })
                        .catch();
            }
        </script>
    </body>
</html>