<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="modelo.dao.ClienteDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.dto.Cliente" %>

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
        <link href="resources/css/adminDasboard.css" rel="stylesheet" type="text/css"/>      
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Clientes</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.38/sweetalert2.min.css" />
        <link href="resources/css/AdmClientes.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/resources/scrip/cliente.js"></script>

    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>

        <%!

        %>
        <div class="container">
            <hr>
            <div class="row align-items-start">
                <div class="col-9"><h1>Gestión de Clientes</h1></div>
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
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>N° Documento</th>
                    <th>Acciones</th>
                    </thead>
                    <tbody>
                        <%
                            ClienteDAO clienteDAO = new ClienteDAO();
                            ArrayList<Cliente> listaCliente = clienteDAO.mostrarClientes();

                            for (Cliente elem : listaCliente) {

                        %>
                        <tr data-fechan="<%= elem.getFechaNacimiento()%>" data-usuario="<%= elem.getUsuario()%>" data-correo="<%= elem.getCorreo()%>" data-contrasena="<%= elem.getContraseña()%>">
                            <td class="idcliente"><%= elem.getCodCliente()%></td>
                            <td class="nombres"><%= elem.getNombres()%></td>
                            <td class="apellidop"><%= elem.getApePaterno()%></td>
                            <td class="apellidom"><%= elem.getApeMaterno()%></td>
                            <td class="ndocumento"><%= elem.getDNI()%></td>
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
                        <h5 class="modal-title" id="exampleModalLabel">Datos Cliente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.servletContext.contextPath}/ClienteServlet" method="POST" role="form">
                            <div class="row">
                                <div class="col-6">
                                    <label>Código de Cliente</label>
                                    <input type="text" name="txtIdCliente" class="form-control" id="txtIdCliente" value="0" readonly="true">
                                </div>
                                <div class="col-6">
                                    <label>N° Documento</label>
                                    <input type="text" name="txtNdocumento" class="form-control" id="txtNdocumento">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Nombres</label>
                                    <input type="text" name="txtNombres" class="form-control" id="txtNombres">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Apellido Paterno</label>
                                    <input type="text" name="txtApellidoP" class="form-control" id="txtApellidoP">
                                </div>
                                <div class="col-6">
                                    <label>Apellido Materno</label>
                                    <input type="text" name="txtApellidoM" class="form-control" id="txtApellidoM">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Fecha Nacimiento</label>
                                    <input type="date" name="txtFechaN" placeholder="DD-MM-YYYY" class="form-control" id="txtFechaN">
                                </div>
                                <div class="col-6">
                                    <label>Usuario</label>
                                    <input type="text" name="txtUsuario" class="form-control" id="txtUsuario">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <label>Correo Electronico</label>
                                    <input type="text" name="txtCorreo" class="form-control" id="txtCorreo">
                                </div>
                                <div class="col-6">
                                    <label>Contraseña</label>
                                    <input type="text" name="txtContrasena" class="form-control" id="txtContrasena">
                                </div>
                            </div>
                            <br>
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

        <!-- JAVASCRIPT del cliente.jsp JAVASCRIPT del cliente.jsp JAVASCRIPT del cliente.jsp JAVASCRIPT del cliente.jsp JAVASCRIPT del cliente.jsp JAVASCRIPT del cliente.jsp -->
        <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js" ></script>
        <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.4.38/sweetalert2.all.min.js"></script>           

        <script>
            var contextPath = '${pageContext.servletContext.contextPath}';
        </script>
        <script src="${pageContext.servletContext.contextPath}/resources/scrip/cliente.js"></script> 
    </body>
</html>
