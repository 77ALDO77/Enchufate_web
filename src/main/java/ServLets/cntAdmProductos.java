package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.CategoriaDAO;
import modelo.dao.LocalesDAO;
import modelo.dao.ProveedorDAO;
import modelo.dto.Categoria;
import modelo.dto.Locales;
import modelo.dto.Proveedor;

public class cntAdmProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) //evalúa las peticiones que ha hecho el usuario
            throws ServletException, IOException {
        List<Categoria> listaCat = new CategoriaDAO().getList();
        List<Proveedor> listaProv = new ProveedorDAO().getList();
        String accion = request.getParameter("accion");
        Boolean redireccionado = (Boolean) request.getSession().getAttribute("redireccionado");
        if (accion != null) {
            if (accion.equals("AdmProductos")) {
                if (redireccionado == null || !redireccionado) {
                    response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");
                    request.getSession().setAttribute("redireccionado", true);
                } else {
                    listaCat = new CategoriaDAO().getList();
                    request.setAttribute("listaCategoria", listaCat);
                    request.setAttribute("lista", listaCat);

                    listaProv = new ProveedorDAO().getList();
                    request.setAttribute("listaProveedor", listaProv);
                    request.setAttribute("lista", listaProv);

                    request.getRequestDispatcher("/AdmProductos.jsp").forward(request, response);
                    request.getSession().removeAttribute("redireccionado");
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
