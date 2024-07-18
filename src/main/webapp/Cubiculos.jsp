<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>Servicio de Cubículo</title>
    <link href="resources/css/inicio.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/resources/img/inicio/fondoenchufate.png') no-repeat center center fixed;
            background-size: cover;
        }
        .cubicles {
            text-align: center;
            padding: 50px 20px;
        }
        .cubicle-container {
            display: flex;
            justify-content: space-around;
            margin: 20px 0;
        }
        .cubicle {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 300px;
        }
        .cubicle img {
            max-width: 100%;
            border-radius: 10px;
        }
        .cubicle h3 {
            margin-top: 10px;
            font-size: 24px;
        }
        .cubicle p {
            font-size: 18px;
            margin: 10px 0;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            color: #fff;
            background-color: #007BFF;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        
    </style>
</head>
<body>
    <jsp:include page="components/encabezado.jsp"/>

    <section class="cubicles">
        <h2 class="title-cubicles">Servicio de Cubículo</h2>
        <div class="cubicle-container">
            <div class="cubicle">
                <img src="${pageContext.request.contextPath}/resources/img/inicio/Normal.png" alt="Cubículo General">
                <h3>Cubículo General</h3>
                <p>S/. 5 x hora</p>
                <p>El cubículo general ofrece un espacio tranquilo y cómodo para trabajar o estudiar con acceso a internet de alta velocidad.</p>
            </div>
            <div class="cubicle">
                <img src="${pageContext.request.contextPath}/resources/img/inicio/VIP.png" alt="Cubículo VIP">
                <h3>Cubículo VIP</h3>
                <p>S/. 8 x hora</p>
                <p>El cubículo VIP incluye todas las comodidades del cubículo general, además de un ambiente más privado y servicios adicionales como bebidas gratuitas.</p>
            </div>
            <div class="cubicle">
                <img src="${pageContext.request.contextPath}/resources/img/inicio/VIPUltra.png" alt="Cubículo ULTRA VIP">
                <h3>Cubículo ULTRA VIP</h3>
                <p>S/. 12 x hora</p>
                <p>El cubículo ULTRA VIP ofrece un espacio de lujo con todas las comodidades del cubículo VIP, además de servicios exclusivos como asistencia personalizada y acceso a salas de reuniones.</p>
            </div>
        </div>
    </section>

    <jsp:include page="components/pie.jsp"/>
</body>
</html>
