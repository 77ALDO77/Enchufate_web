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
        <jsp:include page="components/encabezado.jsp"/>
        <main>
            <div class="hero">
                <h1>ENCHUFATE</h1>
                <p>Tu Tiempo, Tu Espacio, Tu Conexi�n</p>
                <a href="#" class="btn">M�s informaci�n</a>
            </div>

            <!-- Nueva Secci�n de Cub�culos -->
            <section class="cubicles">
                <h2>Servicio de Cub�culo</h2>
                <div class="cubicle-container">
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/Normal.png" alt="Cub�culo General">
                        <h3>Cub�culo General</h3>
                        <p>S/. 5 x hora</p>
                    </div>
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/VIP.png" alt="Cub�culo VIP">
                        <h3>Cub�culo VIP</h3>
                        <p>S/. 8 x hora</p>
                    </div>
                    <div class="cubicle">
                        <img src="${pageContext.request.contextPath}/resources/img/inicio/VIPUltra.png" alt="Cub�culo ULTRA VIP">
                        <h3>Cub�culo ULTRA VIP</h3>
                        <p>S/. 12 x hora</p>
                    </div>
                </div>
                <a href="#" class="btn">Tienda</a>
            </section>
        </main>
        <jsp:include page="components/pie.jsp"/>
    </body>
</html>