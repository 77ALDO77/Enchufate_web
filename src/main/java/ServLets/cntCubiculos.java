/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import com.google.gson.Gson;
import modelo.dao.CubiculoDAO;
import modelo.dao.ReservaDAO;
import modelo.dao.ClienteDAO;
import modelo.dto.Cubiculo;
import modelo.dto.Reserva;
import modelo.dto.Cliente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author aldom
 */
public class cntCubiculos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Gson gson = new Gson();

        if (accion != null) {
            switch (accion) {
                case "listar":
                    List<Cubiculo> cubiculos = cubiculoDAO.obtenerCubiculos();
                    String jsonCubiculos = gson.toJson(cubiculos);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonCubiculos);
                    break;
                case "crearReserva":
                    String dniCliente = request.getParameter("dniCliente");
                    Cliente cliente = clienteDAO.obtenerClientePorDNI(dniCliente);
                    int codEmpleado = 1; // Supongamos que el empleado está registrado y su código es 1
                    int codCubiculo = Integer.parseInt(request.getParameter("codCubiculo"));
                    Date fecha = new Date(System.currentTimeMillis());
                    Time hora = Time.valueOf(LocalTime.now());
                    int duracion = Integer.parseInt(request.getParameter("duracion"));

                    Reserva reserva = new Reserva();
                    reserva.setCodCliente(cliente.getCodCliente());
                    reserva.setCodEmpleado(codEmpleado);
                    reserva.setCodCubiculo(codCubiculo);
                    reserva.setFecha(fecha);
                    reserva.setHora(hora);
                    reserva.setDuracion(duracion);

                    reservaDAO.crearReserva(reserva);
                    cubiculoDAO.actualizarEstadoCubiculo(codCubiculo, "Ocupado");
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "obtenerTiempoRestante":
                    codCubiculo = Integer.parseInt(request.getParameter("codCubiculo"));
                    int tiempoRestante = reservaDAO.obtenerTiempoRestante(codCubiculo);
                    if (tiempoRestante <= 0) {
                        cubiculoDAO.actualizarEstadoCubiculo(codCubiculo, "Disponible");
                    }
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"tiempoRestante\":" + tiempoRestante + "}");
                    break;
                case "obtenerCubiculo":
                    codCubiculo = Integer.parseInt(request.getParameter("codCubiculo"));
                    Cubiculo cubiculo = cubiculoDAO.obtenerCubiculoPorCodigo(codCubiculo);
                    String jsonCubiculo = gson.toJson(cubiculo);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonCubiculo);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no proporcionada");
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
        return "Servlet que maneja los cubículos";
    }// </editor-fold>

}
