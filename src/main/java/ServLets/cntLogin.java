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
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.authenticate(correo, contrasena);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            session.setAttribute("codCliente", customer.getCodcliente());
            response.sendRedirect("Inicio.jsp");
        } else {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleado = empleadoDAO.authenticate(correo, contrasena);

            if (empleado != null) {
                HttpSession session = request.getSession();
                session.setAttribute("empleado", empleado);
                response.sendRedirect("AdministracionCubiculos.jsp");
            } else {
                request.setAttribute("errorMessage", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

