
package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.EmpleadoDAO;
import modelo.dto.Empleado;
/**
 *
 * @author Benja
 */
@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Captura de datos                       
            int idEmpleado = Integer.parseInt(request.getParameter("txtidEmpleado"));
            int idArea = Integer.parseInt(request.getParameter("txtidArea"));
            int idLocal = Integer.parseInt(request.getParameter("txtidLocal"));
            String Nombres = request.getParameter("txtNombres");
            String Apellidos = request.getParameter("txtApellidos");
            String FechaN = request.getParameter("txtFechaN");
            int Dni = Integer.parseInt(request.getParameter("txtDni"));
            String Sexo = request.getParameter("txtSexo");
            int Celular = Integer.parseInt(request.getParameter("txtCelular"));            
            double Salario = Double.parseDouble(request.getParameter("txtSalario"));            
            String Correo = request.getParameter("txtCorreo");            
            String clave = request.getParameter("txtClave");

            String mensaje = "Error";
            int res;

            Empleado em = new Empleado(idEmpleado, idLocal, idArea, Nombres, Apellidos, FechaN, Dni, Sexo, Celular, Salario, Correo, clave);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            if (request.getParameter("btnGuardar") != null) {
                res = empleadoDAO.insertarEmpleado(em);
                if (res != 0) {
                    mensaje = "Registro Agregado";
                    // Redireccionar a AdmEmpleados.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmEmpleados.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            } else if (request.getParameter("btnEditar") != null) {
                res = empleadoDAO.modificarEmpleado(em);
                if (res != 0) {
                    mensaje = "Registro Modificado";
                    // Redireccionar a AdmEmpleados.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmEmpleados.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            } else if (request.getParameter("btnEliminar") != null) {
                res = empleadoDAO.eliminarEmpleado(em);
                if (res != 0) {
                    mensaje = "Registro Eliminado";
                    // Redireccionar a AdmEmpleados.jsp después de registrar exitosamente
                    response.sendRedirect("/EnchufateWeb/AdmEmpleados.jsp");
                    return; // Terminar la ejecución del método después de la redirección
                }
            }
            request.setAttribute("message", mensaje);
            request.getRequestDispatcher("../AdmEmpleados.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            e.printStackTrace(); // Esto imprimirá la pila de llamadas y puede ayudarte a identificar el problema.
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}