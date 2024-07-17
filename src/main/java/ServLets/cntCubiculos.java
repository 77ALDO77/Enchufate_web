package ServLets;

import com.google.gson.Gson;
import modelo.dao.CubiculoDAO;
import modelo.dto.Cubiculo;
import modelo.dto.Reserva;
import modelo.dto.Locales;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class cntCubiculos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listar":
                    listarCubiculos(request, response);
                    break;
                case "listarLocales":
                    listarLocales(request, response);
                    break;
                case "crearReserva":
                    crearReserva(request, response);
                    break;
                case "obtenerCubiculo":
                    obtenerCubiculo(request, response);
                    break;
                case "obtenerTiempoRestante":
                    obtenerTiempoRestante(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no proporcionada");
        }
    }

    private void listarCubiculos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codLocal = Integer.parseInt(request.getParameter("codLocal"));
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        List<Cubiculo> cubiculos = cubiculoDAO.obtenerCubiculosPorLocal(codLocal);
        Gson gson = new Gson();
        String json = gson.toJson(cubiculos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void listarLocales(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        List<Locales> locales = cubiculoDAO.obtenerLocales();
        Gson gson = new Gson();
        String json = gson.toJson(locales);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void crearReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        Reserva reserva = new Reserva();
        reserva.setCodCliente(Integer.parseInt(request.getParameter("codCliente"))); // Se asume que el parámetro es codCliente en lugar de dniCliente
        reserva.setCodEmpleado(1); // Proporciona el ID del empleado actual
        reserva.setCodCubiculo(Integer.parseInt(request.getParameter("codCubiculo")));
        reserva.setFecha(Date.valueOf(request.getParameter("fecha")));
        reserva.setHoraInicio(Timestamp.valueOf(request.getParameter("horaInicio")));
        reserva.setHoraFin(Timestamp.valueOf(request.getParameter("horaFin")));
        reserva.setTiempo(request.getParameter("tiempo"));
        cubiculoDAO.crearReserva(reserva);
    }

    private void obtenerCubiculo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codCubiculo = Integer.parseInt(request.getParameter("codCubiculo"));
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        Cubiculo cubiculo = cubiculoDAO.obtenerCubiculoPorCodigo(codCubiculo);
        Gson gson = new Gson();
        String json = gson.toJson(cubiculo);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void obtenerTiempoRestante(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codCubiculo = Integer.parseInt(request.getParameter("codCubiculo"));
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        int tiempoRestante = cubiculoDAO.obtenerTiempoRestante(codCubiculo);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"tiempoRestante\":" + tiempoRestante + "}");
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
        return "Servlet que maneja los cubículos";
    }
}
