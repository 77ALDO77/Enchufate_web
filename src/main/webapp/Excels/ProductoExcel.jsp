<%@ page import="java.sql.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.*" %>
<%@ page import="org.apache.poi.ss.usermodel.*" %>
<%@ page import="org.apache.poi.util.IOUtils" %>
<%@ page import="java.io.*" %>

<%
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment; filename=categorias_habitacion.xls");

// Conexión a la base de datos
String url = "jdbc:mysql://localhost:3306/enchufate?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&characterEncoding=UTF-8";
String username = "root";
String password = "";
Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;

OutputStream outputStream = null; // Cambio de nombre de la variable

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection(url, username, password);

    // Consulta SQL para obtener datos de la tabla Categoriahabitacion
    String sql = "SELECT p.CodProducto, p.Nombre, p.Descripcion, p.Precio, p.FechaVencimiento, c.Nombre, pro.Nombre FROM Producto p INNER JOIN Categoria c ON p.CodCategoria = c.CodCategoria INNER JOIN proveedor pro ON p.CodProveedor=pro.CodProveedor;";
    stmt = conn.prepareStatement(sql);
    rs = stmt.executeQuery();

    // Crear el libro de Excel
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Productos");
    
    
    // Agregar logo
    InputStream inputStream = new FileInputStream(application.getRealPath("/resources/img/AdmNav/Logo.png"));
    byte[] bytes = IOUtils.toByteArray(inputStream);
    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
    inputStream.close();

    CreationHelper helper = workbook.getCreationHelper();
    Drawing<?> drawing = sheet.createDrawingPatriarch();
    ClientAnchor anchor = helper.createClientAnchor();
    anchor.setCol1(0);
    anchor.setRow1(0);
    Picture pict = drawing.createPicture(anchor, pictureIdx);
    pict.resize();
    

    // Crear la fila de encabezado
    HSSFRow headerRow = sheet.createRow(8);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nombre");
    headerRow.createCell(2).setCellValue("Descripcion");
    headerRow.createCell(3).setCellValue("Precio");
    headerRow.createCell(4).setCellValue("Fecha de Nacimiento");
    headerRow.createCell(5).setCellValue("Categoria");
    headerRow.createCell(6).setCellValue("Proveedor");

    // Llenar el libro con los datos de la base de datos
    int rowNum = 9;
    while (rs.next()) {
        HSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(rs.getString("p.CodProducto"));
        row.createCell(1).setCellValue(rs.getString("p.Nombre"));
        row.createCell(2).setCellValue(rs.getString("p.Descripcion"));
        row.createCell(3).setCellValue(rs.getString("p.Precio"));
        row.createCell(4).setCellValue(rs.getString("p.FechaVencimiento"));
        row.createCell(5).setCellValue(rs.getString("c.Nombre"));
        row.createCell(6).setCellValue(rs.getString("pro.Nombre"));
    }

    // Escribir el libro en el flujo de salida
    workbook.write(response.getOutputStream()); // Cambio de nombre de la variable
} catch (Exception e) {
    out.println("Error: " + e.getMessage());
} finally {
    try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
%>