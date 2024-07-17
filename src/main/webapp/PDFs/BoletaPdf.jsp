<%@ page import="java.sql.*" %>
<%@ page import="com.itextpdf.text.*" %>
<%@ page import="com.itextpdf.text.pdf.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.itextpdf.text.Image" %>

<%
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=boleta.pdf");

    // Conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/enchufate?useTimeZone=true&serverTimezone=UTC&autoReconnect=true";
    String username = "root";
    String password = "root";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    OutputStream outputStream = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);

        // Consulta SQL para obtener datos de la tabla cliente y producto
        String sql = "SELECT * FROM carrito INNER JOIN cliente ON carrito.CodCliente = cliente.CodCliente INNER JOIN producto ON carrito.CodProducto = producto.CodProducto";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        // Crear el documento PDF con márgenes
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Añade el logo al encabezado
        String logoPath = "/resources/img/AdmNav/Logo.png"; // Ruta al logo
        Image logo = Image.getInstance(getServletContext().getRealPath(logoPath));
        logo.scaleToFit(100, 100); // Ajusta tamaño del logo según sea necesario
        logo.setAlignment(Element.ALIGN_CENTER); // Centra horizontalmente el logo
        document.add(logo);

        // Información ficticia de la empresa Enchufate
        Font empresaFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        Paragraph empresaInfo = new Paragraph();
        empresaInfo.setFont(empresaFont);
        empresaInfo.add(new Chunk("Empresa: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        empresaInfo.add("Enchufate S.A.C\n");
        empresaInfo.add(new Chunk("RUC: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        empresaInfo.add("20234567891\n"); // RUC ficticio
        empresaInfo.add(new Chunk("Dirección: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        empresaInfo.add("Av. Principal 123, Lima, Perú\n");
        empresaInfo.add(new Chunk("Teléfono: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        empresaInfo.add("+51 123 456 789\n");
        empresaInfo.add(new Chunk("Correo: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        empresaInfo.add("info@enchufate.com\n\n");
        document.add(empresaInfo);

        // Título de la boleta
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLUE);
        Paragraph title = new Paragraph("Boleta de Compra", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Fuente para los datos del cliente
        Font clientFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        // Variables para la suma total de los productos
        double sumaTotal = 0.0;

        // Datos del cliente (obtenidos solo una vez)
        if (rs.next()) {
            String nombreCliente = rs.getString("cliente.Nombres") + " " + rs.getString("cliente.ApePaterno") + " " + rs.getString("cliente.ApeMaterno");
            String dniCliente = rs.getString("cliente.Dni");
            String correoCliente = rs.getString("cliente.Correo");

            Paragraph clienteInfo = new Paragraph();
            clienteInfo.setFont(clientFont);
            clienteInfo.add(new Chunk("Cliente: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            clienteInfo.add(nombreCliente + "\n");
            clienteInfo.add(new Chunk("DNI: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            clienteInfo.add(dniCliente + "\n");
            clienteInfo.add(new Chunk("Correo: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            clienteInfo.add(correoCliente + "\n\n");
            document.add(clienteInfo);
        }

        // Detalles de los productos comprados
        do {
            String nombreProducto = rs.getString("producto.Nombre");
            String precioProducto = rs.getString("producto.Precio");
            String descripcionProducto = rs.getString("producto.Descripcion");
            String fechaVencimientoProducto = rs.getString("producto.FechaVencimiento");
            String cantidadProducto = rs.getString("carrito.Cantidad");
            String totalPagar = rs.getString("carrito.Total");

            Paragraph productoInfo = new Paragraph();
            productoInfo.setFont(clientFont);
            productoInfo.add(new Chunk("Producto: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(nombreProducto + "\n");
            productoInfo.add(new Chunk("Precio: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(precioProducto + "\n");
            productoInfo.add(new Chunk("Descripción: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(descripcionProducto + "\n");
            productoInfo.add(new Chunk("Fecha de Vencimiento: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(fechaVencimientoProducto + "\n");
            productoInfo.add(new Chunk("Cantidad: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(cantidadProducto + "\n");
            productoInfo.add(new Chunk("Total a Pagar: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            productoInfo.add(totalPagar + "\n\n");
            document.add(productoInfo);

            // Sumar el total a pagar al total acumulado
            sumaTotal += Double.parseDouble(totalPagar);
        } while (rs.next());

        // Mostrar el pago total al final de la boleta
        Paragraph totalPagoInfo = new Paragraph();
        totalPagoInfo.setFont(clientFont);
        totalPagoInfo.add(new Chunk("Pago Total de los Productos: ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        totalPagoInfo.add(String.format("%.2f", sumaTotal) + "\n\n");
        document.add(totalPagoInfo);

        document.close();

        // Enviar el PDF generado como respuesta al cliente
        outputStream = response.getOutputStream();
        baos.writeTo(outputStream);
        outputStream.flush();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
%>
