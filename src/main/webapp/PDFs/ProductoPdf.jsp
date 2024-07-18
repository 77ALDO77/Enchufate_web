<%@ page import="java.sql.*" %>
<%@ page import="com.itextpdf.text.*" %>
<%@ page import="com.itextpdf.text.pdf.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.itextpdf.text.Image" %>

<%
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=clientes.pdf");

// Conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/enchufate?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&characterEncoding=UTF-8";
    String username = "root";
    String password = "";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    OutputStream outputStream = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);

        // Consulta SQL para obtener datos de la tabla cliente
        String sql = "SELECT p.CodProducto, p.Nombre, p.Descripcion, p.Precio, p.FechaVencimiento, c.Nombre, pro.Nombre FROM Producto p INNER JOIN Categoria c ON p.CodCategoria = c.CodCategoria INNER JOIN proveedor pro ON p.CodProveedor=pro.CodProveedor;";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        // Crear el documento PDF con márgenes
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Añade el logo al encabezado
        String logoPath = "/resources/img/AdmNav/Logo.png"; // Ruta al logo
        Image logo = Image.getInstance(getServletContext().getRealPath(logoPath));
        logo.scaleToFit(100, 100); // Ajusta tamaño del logo según sea necesario
        logo.setAlignment(Element.ALIGN_CENTER); // Centra horizontalmente el logo
        document.add(logo);

        // Añadir título
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Listado de Productos", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Crear tabla con cabecera y definir colores
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        PdfPCell cell;

        // Encabezados de la tabla
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        BaseColor headerColor = new BaseColor(0, 121, 182);
        String[] headers = {"ID", "Nombre", "Descripcion", "Precio", "Fecha de Nacimiento", "Categoria", "Proveedor"};

        for (String header : headers) {
            cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(headerColor);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }

        // Llenar la tabla con los datos de la base de datos
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
        while (rs.next()) {
            table.addCell(new PdfPCell(new Phrase(rs.getString("p.CodProducto"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("p.Nombre"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("p.Descripcion"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("p.Precio"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("p.FechaVencimiento"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("c.Nombre"), cellFont)));
            table.addCell(new PdfPCell(new Phrase(rs.getString("pro.Nombre"), cellFont)));
        }

        // Agregar la tabla al documento
        document.add(table);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
%>
