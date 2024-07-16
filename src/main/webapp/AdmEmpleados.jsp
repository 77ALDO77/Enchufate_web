<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="modelo.dto.Empleado" %>
<%@ page import="modelo.dao.EmpleadoDAO" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.itextpdf.text.*" %>
<%@ page import="com.itextpdf.text.pdf.*" %>
<%@ page import="org.apache.poi.ss.usermodel.*" %>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/AdmEmpleados.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Empleados</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.38/sweetalert2.min.css" />
        <script src="${pageContext.servletContext.contextPath}/resources/scrip/empleado.js"></script>

    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>

        <%!
            
        %>
        <div class="container">

            <hr>
            <div class="row align-items-start">
                <div class="col-9"><h1>Empleados</h1></div>
                <div class="col-3 align-self-center">
                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-success btnAdd" data-bs-toggle="modal" data-bs-target="#exampleModal">Registrar</button>
                        <button type="button" class="btn btn-primary btnGenerarPDF" onclick="generarPDF()">Generar PDF</button>
                        <button type="button" class="btn btn-primary btnGenerarExcel" onclick="generarExcel()">Generar EXCEL</button>
                    </div>                    
                </div>
            </div>


            <hr>
            <div class="table-responsive">
                <table class="table table-striped" id="mydataTable">
                    <thead>
                    <th>Id</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Dni</th>
                    <th>Area</th>
                    <th>Nombre del Local</th>
                    <th>Acciones</th>
                    </thead>
                    <tbody>
                        <%
                            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                            ArrayList<Empleado> listaEmpleado = empleadoDAO.mostrarEmpleados();

                            for (Empleado elem : listaEmpleado) {

                        %>
                        <tr data-fechan="<%= elem.getFechaNacimiento() %>" data-sexo="<%= elem.getSexo()%>" data-celular="<%= elem.getCelular()%>" data-salario="<%= elem.getSalario()%>" data-correo="<%= elem.getCorreo() %>" data-clave="<%=elem.getClave()%>">
                            <td class="idempleado"><%= elem.getCodEmpleado()%></td>
                            <td class="nombres"><%= elem.getNombre()%></td>
                            <td class="apellidos"><%= elem.getApellidos()%></td>
                            <td class="dni"><%= elem.getDni()%></td>
                            <td class="descripcionarea"><%= elem.getClase_area().getDescripcion()%></td>
                            <td class="nombrelocal"><%= elem.getClase_local().getNombre()%></td>
                            <td>
                                <button type="button" class="btn btn-dark btnEditar" data-bs-toggle="modal" data-bs-target="#exampleModal">Actualizar</button>
                                <button type="button" class="btn btn-danger btnEliminar" data-bs-toggle="modal" data-bs-target="#exampleModal">Eliminar</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Button trigger modal -->
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Datos del Empleado</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.servletContext.contextPath}/EmpleadoServlet" method="POST" role="form">
                            <div class="row">
                                <div class="col-6">
                                    <label>Código de Empleado</label>
                                    <input type="text" name="txtidEmpleado" class="form-control" id="txtidEmpleado" value="0" readonly="true">
                                </div>                    
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Nombre del Local</label>
                                    <select name="txtidLocal" id="txtidLocal" class="form-select">
                                        <option value="0">Seleccionar Nomb...</option>
                                        <%
                                            ArrayList<Empleado> lista = empleadoDAO.mostrarLocales();
                                            for (Empleado elem : lista) {
                                        %>
                                        <option  value="<%= elem.getClase_local().getCodLocal()%>">
                                            <%= elem.getClase_local().getNombre()%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-6">
                                    <label>Area</label>
                                    <select name="txtidArea" id="txtidArea" class="form-select">
                                        <option value="0">Seleccionar Area...</option>
                                        <%
                                            ArrayList<Empleado> lista2 = empleadoDAO.mostrarAreas();
                                            for (Empleado elem : lista2) {
                                        %>
                                        <option value="<%= elem.getClase_area().getCodArea()%>">
                                            <%= elem.getClase_area().getDescripcion()%>
                                        </option>

                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Nombres</label>
                                    <input type="text" name="txtNombres" class="form-control" id="txtNombres">
                                </div>
                                <div class="col-6">
                                    <label>Apellidos</label>
                                    <input type="text" name="txtApellidos" class="form-control" id="txtApellidos">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Nª Dni</label>
                                    <input type="text" name="txtDni" class="form-control" id="txtDni">
                                </div>
                                <div class="col-6">
                                    <label for="sexo">Sexo</label>
                                    <select name="txtSexo" id="txtSexo" class="form-select">
                                        <option value="Masculino">Masculino</option>
                                        <option value="Femenino">Femenino</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Fecha de Nacimiento</label>
                                    <input type="date" name="txtFechaN" class="form-control" id="txtFechaN">
                                </div>
                                <div class="col-6">
                                    <label>Celular</label>
                                    <input type="text" name="txtCelular" class="form-control" id="txtCelular">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Salario</label>
                                    <input type="text" name="txtSalario" class="form-control" id="txtSalario">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Correo</label>
                                    <input type="text" name="txtCorreo" class="form-control" id="txtCorreo">
                                </div>
                                <div class="col-6">
                                    <label>Clave</label>
                                    <input type="text" name="txtClave" class="form-control" id="txtClave">
                                </div>
                            </div><br>
                            <div class="row">
                                <div class="col-12">
                                    <button type="submit" name="btnGuardar" class="btn btn-success btnGuardarModal">Registrar</button>
                                    <button type="submit" name="btnEditar" class="btn btn-dark btnEditarModal">Actualizar</button>
                                    <button type="submit" name="btnEliminar" class="btn btn-danger btnEliminarModal">Eliminar</button>
                                    <button type="button" class="btn btn-info btnCancelarModal" data-bs-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js" ></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.38/sweetalert2.all.min.js"></script>   
        
        
    </body>
</html>