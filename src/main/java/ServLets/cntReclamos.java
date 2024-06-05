/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ReclamosDAO;
import modelo.dto.Reclamos;

/**
 *
 * @author aldom
 */
public class cntReclamos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            if (accion.equals("Enviar")) {
                String nombre_cliente = request.getParameter("name");
                String correo_reclamo = request.getParameter("email");
                String dni_reclamo = request.getParameter("ID");
                String fecha_reclamo = request.getParameter("fecha_reclamo");
                String asunto_reclamo = request.getParameter("Asunto");
                String contenido_reclamo = request.getParameter("ContenidoReclamo");

//                request.setAttribute("name",nombre_cliente);
//                request.setAttribute("email",correo_reclamo);
//                request.setAttribute("ID",dni_reclamo);
//                request.setAttribute("fecha_reclamo",fecha_reclamo);
//                request.setAttribute("Asunto",asunto_reclamo);
//                request.setAttribute("ContenidoReclamo",contenido_reclamo);
//
//                request.setAttribute("name","");
//                request.setAttribute("email","");
//                request.setAttribute("ID","");
//                request.setAttribute("fecha_reclamo","");
//                request.setAttribute("Asunto","");
//                request.setAttribute("ContenidoReclamo","");

                Reclamos c = new Reclamos();
                c.setNombre_cliente(nombre_cliente);
                c.setCorreo_reclamo(correo_reclamo);
                c.setDni_reclamo(dni_reclamo);
                c.setFecha_reclamo(fecha_reclamo);
                c.setAsunto_reclamo(asunto_reclamo);
                c.setContenido_reclamo(contenido_reclamo);
                

                ReclamosDAO reclamosDAO = new ReclamosDAO();
                String resp = reclamosDAO.insert(c);

//                String resp = new ClaimsDAO().insert(c);
                request.getRequestDispatcher("Reclamos.jsp").forward(request, response);
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
