package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.CategoriaDAO;
import modelo.dao.ProductoDAO;
import modelo.dao.ProveedorDAO;
import modelo.dto.Categoria;
import modelo.dto.Producto;
import modelo.dto.Proveedor;
import conexion.ConectaBD;
import javax.servlet.http.HttpSession;

public class cntAdmProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) //evalúa las peticiones que ha hecho el usuario
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        List<Categoria> listaCat = new CategoriaDAO().getList();
        List<Proveedor> listaProv = new ProveedorDAO().getList();
        List<Producto> listaProd = new ProductoDAO().getList();
        request.setAttribute("listaProveedor", listaProv);
        Boolean redireccionado = (Boolean) request.getSession().getAttribute("redireccionado");
        if (accion != null) {
            if (accion.equals("AdmProductos")) {
                // Establece los atributos de la solicitud
                request.setAttribute("listaCategoria", listaCat);
                request.setAttribute("listaProveedor", listaProv);
                request.setAttribute("listaProducto", listaProd);

                // Despacha la solicitud al JSP
                request.getRequestDispatcher("/AdmProductos.jsp").forward(request, response);
            } else if (accion.equals("Registrar")) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String fecha = request.getParameter("fechavencimiento");
                String precioStr = request.getParameter("precio");
                Double precio = null;

                // Verificar si el precio es válido
                try {
                    precio = Double.parseDouble(precioStr);
                } catch (NumberFormatException e) {
                    precio = null;
                }
                int codCategoria = Integer.parseInt(request.getParameter("cboCategoria"));
                int codProveedor = Integer.parseInt(request.getParameter("cboProveedor"));

                if (nombre == null || nombre.isEmpty()
                        || descripcion == null || descripcion.isEmpty()
                        || precio == null) {

                    String mensajeAdvertencia = "Llene todos los campos obligatorios.";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeAdvertencia", mensajeAdvertencia);

                    // Redirigir de nuevo al formulario de registro
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                } else if (precio <= 0 || precio > 100000000) {
                    // Verificar si el precio es menor o igual a 0
                    String mensajeAdvertencia = "Ingrese un precio válido.";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeAdvertencia", mensajeAdvertencia);

                    // Redirigir de nuevo al formulario de registro
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                } else {
                    // Crear objeto Producto con los datos del formulario
                    Producto producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setDescripcion(descripcion);
                    if (fecha != null && !fecha.isEmpty()) {
                        producto.setFechavencimiento(fecha);
                    } else {
                        producto.setFechavencimiento(null); // o no establecer la fecha si es nula
                    }
                    producto.setPrecio(precio);
                    producto.setCodcategoria(codCategoria); // Asumiendo que setCodcategoria y setCodproveedor existen en la clase Producto
                    producto.setCodproveedor(codProveedor);

                    // Llamar al DAO para registrar o actualizar el producto
                    ProductoDAO dao = new ProductoDAO();
                    String respuesta = dao.RegistrarProducto(producto);

                    response.getWriter().println(respuesta);

                    request.setAttribute("listaProducto", listaProd);

                    // Establecer el mensaje de confirmación en la sesión
                    String mensajeConfirmacion = "¡Producto '" + nombre + "' registrado correctamente!";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeConfirmacion", mensajeConfirmacion);

                    // Redirigir a la acción AdmProductos
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                }
            } else if (accion.equals("editar")) {
                Integer cod = Integer.valueOf(request.getParameter("codigoproducto"));
                Producto p = new ProductoDAO().ObtenerProducto(cod);

                request.setAttribute("nombre", p.getNombre());
                request.setAttribute("descripcion", p.getDescripcion());
                request.setAttribute("fechavencimiento", p.getFechavencimiento());
                request.setAttribute("precio", "" + "" + p.getPrecio());
                request.setAttribute("categoria", "" + p.getCategoria().getCodCategoria());
                request.setAttribute("proveedor", "" + p.getProveedor().getCodProveedor());
                request.setAttribute("codproducto", "" + p.getCodproducto());
                request.setAttribute("listaCategoria", listaCat);
                request.setAttribute("listaProveedor", listaProv);
                request.setAttribute("listaProducto", listaProd);
                request.setAttribute("showModal", true);
                request.setAttribute("isEdit", true); // Indicar que se está editando

                request.getRequestDispatcher("./AdmProductos.jsp").forward(request, response);
            } else if (accion.equals("agregar")) {
                request.setAttribute("showModal", true);
                request.setAttribute("isEdit", false); // Indicar que se está agregando

                request.getRequestDispatcher("./AdmProductos.jsp").forward(request, response);
            } else if (accion.equals("Actualizar")) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String fecha = request.getParameter("fechavencimiento");
                String precioStr = request.getParameter("precio");
                Double precio = null;

                // Verificar si el precio es válido
                try {
                    precio = Double.parseDouble(precioStr);
                } catch (NumberFormatException e) {
                    precio = null;
                }
                int codCategoria = Integer.parseInt(request.getParameter("cboCategoria"));
                int codProveedor = Integer.parseInt(request.getParameter("cboProveedor"));

                // Obtener el código del producto
                int codProducto = Integer.parseInt(request.getParameter("codproducto"));

                if (nombre == null || nombre.isEmpty()
                        || descripcion == null || descripcion.isEmpty()
                        || precio == null) {

                    String mensajeAdvertencia = "Llene todos los campos obligatorios.";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeAdvertencia", mensajeAdvertencia);

                    // Redirigir de nuevo al formulario de registro
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                } else if (precio <= 0 || precio > 100000000) {
                    // Verificar si el precio es menor o igual a 0
                    String mensajeAdvertencia = "Ingrese un precio válido.";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeAdvertencia", mensajeAdvertencia);

                    // Redirigir de nuevo al formulario de registro
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                } else {
                    // Crear objeto Producto con los datos del formulario
                    Producto producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setDescripcion(descripcion);
                    if (fecha != null && !fecha.isEmpty()) {
                        producto.setFechavencimiento(fecha);
                    } else {
                        producto.setFechavencimiento(null); // o no establecer la fecha si es nula
                    }
                    producto.setPrecio(precio);
                    producto.setCodcategoria(codCategoria); // Asumiendo que setCodcategoria y setCodproveedor existen en la clase Producto
                    producto.setCodproveedor(codProveedor);
                    producto.setCodproducto(codProducto);

                    // Llamar al DAO para registrar o actualizar el producto
                    ProductoDAO dao = new ProductoDAO();
                    String respuesta = dao.ActualizarProducto(producto);

                    response.getWriter().println(respuesta);

                    request.setAttribute("listaProducto", listaProd);

                    // Establecer el mensaje de confirmación en la sesión
                    String mensajeConfirmacion = "¡Producto '" + nombre + "' actualizado correctamente!";
                    HttpSession session = request.getSession();
                    session.setAttribute("mensajeConfirmacion", mensajeConfirmacion);

                    // Redirigir a la acción AdmProductos
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
