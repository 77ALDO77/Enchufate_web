package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.CustomerDAO;
import modelo.dto.Customer;

public class cntCustomer extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if (action != null && action.equals("Crear Cuenta")) {
            String nombre = request.getParameter("nombre");
            String apePaterno = request.getParameter("apepaterno");
            String apeMaterno = request.getParameter("apematerno");
            String dni = request.getParameter("dni");
            String fechaNacimiento = request.getParameter("fechanacimiento");
            String usuario = request.getParameter("usuario");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");

            Customer customer = new Customer();
            customer.setNombre(nombre);
            customer.setApepaterno(apePaterno);
            customer.setApematerno(apeMaterno);
            customer.setDni(dni);
            customer.setFechanacimiento(fechaNacimiento);
            customer.setUsuario(usuario);
            customer.setCorreo(correo);
            customer.setContraseña(contrasena);

            CustomerDAO customerDAO = new CustomerDAO();
            boolean isCreated = customerDAO.insert(customer);

            if (isCreated) {
                // Redirigir a create-account.jsp con un parámetro de éxito
                response.sendRedirect("create-account.jsp?success=true");
            } else {
                // Manejar el error de creación de cuenta, redirigir o mostrar mensaje de error
                response.sendRedirect("create-account.jsp?error=true");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar clientes";
    }
}