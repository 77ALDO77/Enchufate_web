package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.CustomerDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dto.Customer;
import modelo.dto.Empleado;

public class cntLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario de login
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // Autenticar cliente
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.authenticate(correo, contrasena);

        if (customer != null) {
            // Crear una sesión y redirigir a home.jsp
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("Inicio.jsp");
        } else {
            // Autenticar empleado
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleado = empleadoDAO.authenticate(correo, contrasena);

            if (empleado != null) {
                // Crear una sesión y redirigir a adminCubiculos.jsp
                HttpSession session = request.getSession();
                session.setAttribute("empleado", empleado);
                response.sendRedirect("AdministracionCubiculos.jsp");
            } else {
                // Redirigir de nuevo a la página de login con un mensaje de error
                request.setAttribute("errorMessage", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
