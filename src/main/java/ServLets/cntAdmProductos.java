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
        Boolean redireccionado = (Boolean) request.getSession().getAttribute("redireccionado");
        if (accion != null) {
            if (accion.equals("AdmProductos")) {
                if (redireccionado == null || !redireccionado) {
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                    request.getSession().setAttribute("redireccionado", true);
                } else {
                    List<Categoria> listaCat = new CategoriaDAO().getList();
                    request.setAttribute("listaCategoria", listaCat);

                    List<Proveedor> listaProv = new ProveedorDAO().getList();
                    request.setAttribute("listaProveedor", listaProv);

                    List<Producto> listaProd = new ProductoDAO().getList();
                    request.setAttribute("listaProducto", listaProd);

                    request.getRequestDispatcher("./AdmProductos.jsp").forward(request, response);
                    request.getSession().removeAttribute("redireccionado");
                }
            } else if (accion.equals("Registrar")) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String fecha = request.getParameter("fecha");
                Double precio = Double.parseDouble(request.getParameter("precio"));
                int codCategoria = Integer.parseInt(request.getParameter("cboCategoria"));
                int codProveedor = Integer.parseInt(request.getParameter("cboProveedor"));

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
                String respuesta = dao.RegistrarActualizarProducto(producto);

                response.getWriter().println(respuesta);

                List<Categoria> listaCat = new CategoriaDAO().getList();
                request.setAttribute("listaCategoria", listaCat);

                List<Proveedor> listaProv = new ProveedorDAO().getList();
                request.setAttribute("listaProveedor", listaProv);

                List<Producto> listaProd = new ProductoDAO().getList();
                request.setAttribute("listaProducto", listaProd);

                // Establecer el mensaje de confirmación en la sesión
                String mensajeConfirmacion = "¡Producto '" + nombre + "' registrado correctamente!";
                HttpSession session = request.getSession();
                session.setAttribute("mensajeConfirmacion", mensajeConfirmacion);

                // Redirigir a la acción AdmProductos
                response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
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
