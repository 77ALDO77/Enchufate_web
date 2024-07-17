<%@ page import="java.sql.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.*" %>
<%@ page import="java.io.*" %>

<%
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment; filename=categorias_habitacion.xls");

// Conexión a la base de datos
String url = "jdbc:mysql://localhost:3306/enchufate?useTimeZone=true&serverTimezone=UTC&autoReconnect=true";
String username = "root";
String password = "root";
Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;

OutputStream outputStream = null; // Cambio de nombre de la variable

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection(url, username, password);

    // Consulta SQL para obtener datos de la tabla Categoriahabitacion
    String sql = "SELECT * FROM Empleado INNER JOIN Locales ON Empleado.CodLocal = Locales.CodLocal INNER JOIN Area ON Empleado.CodArea = Area.CodArea;";
    stmt = conn.prepareStatement(sql);
    rs = stmt.executeQuery();

    // Crear el libro de Excel
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Clientes");

    // Crear la fila de encabezado
    HSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nombres");
    headerRow.createCell(2).setCellValue("Apellidos");
    headerRow.createCell(3).setCellValue("Fecha de Nacimiento");
    headerRow.createCell(4).setCellValue("DNI");
    headerRow.createCell(5).setCellValue("Sexo");
    headerRow.createCell(6).setCellValue("Salario");
    headerRow.createCell(7).setCellValue("Area");
    headerRow.createCell(8).setCellValue("Nombre del Local");
    headerRow.createCell(9).setCellValue("Correo");
    headerRow.createCell(10).setCellValue("Clave");

    // Llenar el libro con los datos de la base de datos
    int rowNum = 1;
    while (rs.next()) {
        HSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(rs.getString("Empleado.CodEmpleado"));
        row.createCell(1).setCellValue(rs.getString("Empleado.Nombre"));
        row.createCell(2).setCellValue(rs.getString("Empleado.Apellidos"));
        row.createCell(3).setCellValue(rs.getString("Empleado.FechaNacimiento"));
        row.createCell(4).setCellValue(rs.getString("Empleado.Dni"));
        row.createCell(5).setCellValue(rs.getString("Empleado.Sexo"));
        row.createCell(6).setCellValue(rs.getString("Empleado.Salario"));
        row.createCell(7).setCellValue(rs.getString("Area.Descripcion"));
        row.createCell(8).setCellValue(rs.getString("Locales.Nombre"));
        row.createCell(9).setCellValue(rs.getString("Empleado.Correo"));
        row.createCell(10).setCellValue(rs.getString("Empleado.Clave"));
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