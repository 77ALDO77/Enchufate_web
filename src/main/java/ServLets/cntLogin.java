/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.CustomerDAO;
import modelo.dto.Customer;

public class cntLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario de login
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // Crear una instancia del DAO
        CustomerDAO customerDAO = new CustomerDAO();

        // Autenticar usuario
        Customer customer = customerDAO.authenticate(correo, contrasena);

        if (customer != null) {
            // Crear una sesión y redirigir a home.jsp
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("index.jsp");
        } else {
            // Redirigir de nuevo a la página de login con un mensaje de error
            request.setAttribute("errorMessage", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
