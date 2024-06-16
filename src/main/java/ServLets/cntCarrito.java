/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.CarritoDAO;
import modelo.dto.Producto;

public class cntCarrito extends HttpServlet {

    private CarritoDAO carritoDAO;

    public void init() {
        carritoDAO = new CarritoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer codCliente = (Integer) session.getAttribute("codCliente");

        if (codCliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int codProducto = Integer.parseInt(request.getParameter("codProducto"));
            carritoDAO.agregarProducto(codCliente, codProducto, 1);

            // Actualizar el carrito en la sesión
            List<Producto> productosEnCarrito = carritoDAO.getProductosEnCarrito(codCliente);
            session.setAttribute("carrito", productosEnCarrito);

            response.sendRedirect("cntCarrito?action=view");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("market.jsp?mensaje=Error+al+agregar+producto");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer codCliente = (Integer) session.getAttribute("codCliente");

        if (codCliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Producto> productosEnCarrito = carritoDAO.getProductosEnCarrito(codCliente);
        if (productosEnCarrito == null || productosEnCarrito.isEmpty()) {
            request.setAttribute("errorMessage", "El carrito está vacío.");
        } else {
            request.setAttribute("productosEnCarrito", productosEnCarrito);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Carrito.jsp");
        dispatcher.forward(request, response);
    }
}






