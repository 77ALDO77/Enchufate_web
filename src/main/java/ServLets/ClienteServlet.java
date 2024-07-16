package ServLets;


import modelo.dto.Cliente;
import modelo.dao.ClienteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benja
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //Captura de datos                       
            int idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
            String Nombres = request.getParameter("txtNombres");
            String ApellidoP = request.getParameter("txtApellidoP");
            String ApellidoM = request.getParameter("txtApellidoM");
            String Ndocumento = request.getParameter("txtNdocumento");
            Date FechaN = Date.valueOf(request.getParameter("txtFechaN"));
            String Usuario = request.getParameter("txtUsuario");            
            String Correo = request.getParameter("txtCorreo");            
            String Contrasena = request.getParameter("txtContrasena");            
            String mensaje = "Error";
            int res;
            
            Cliente c = new Cliente(idCliente, Nombres, ApellidoP, ApellidoM, Ndocumento, FechaN, Usuario, Correo, Contrasena);
            ClienteDAO clienteDAO = new ClienteDAO();
            
            if(request.getParameter("btnGuardar") != null){
                res = clienteDAO.insertarCliente(c);
                if(res != 0){
                    mensaje = "Registro Agregado";
                    // Redireccionar a clientes.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmClientes.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            }else if(request.getParameter("btnEditar") != null){
                res = clienteDAO.modificarCliente(c);
                if(res != 0){
                    mensaje = "Registro Modificado";
                    // Redireccionar a clientes.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmClientes.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            }else if(request.getParameter("btnEliminar") != null){
                res = clienteDAO.eliminarCliente(c);
                if(res != 0){
                    mensaje = "Registro Eliminado";
                    // Redireccionar a clientes.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmClientes.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            }
            request.setAttribute("message", mensaje);
            request.getRequestDispatcher("../AdmClientes.jsp").forward(request, response);
            
        }catch(Exception e){
            System.out.println("Error "+e.getLocalizedMessage());
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
