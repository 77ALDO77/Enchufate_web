package ServLets;

import com.google.gson.Gson;
import modelo.dao.CubiculoDAO;
import modelo.dao.ReservaDAO;
import modelo.dao.ClienteDAO;
import modelo.dto.Cubiculo;
import modelo.dto.Reserva;
import modelo.dto.Cliente;
import modelo.dto.Local;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
        List<Local> locales = cubiculoDAO.obtenerLocales();
        Gson gson = new Gson();
        String json = gson.toJson(locales);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void crearReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReservaDAO reservaDAO = new ReservaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Reserva reserva = new Reserva();

        int codCliente = clienteDAO.obtenerCodigoPorDNI(request.getParameter("dniCliente"));
        reserva.setCodCliente(codCliente);
        reserva.setCodEmpleado(1); // Proporciona el ID del empleado actual
        reserva.setCodCubiculo(Integer.parseInt(request.getParameter("codCubiculo")));
        reserva.setFecha(Date.valueOf(request.getParameter("fecha")));
        reserva.setHoraInicio(Time.valueOf(request.getParameter("horaInicio")));
        reserva.setHoraFin(Time.valueOf(request.getParameter("horaFin")));
        reserva.setDuracion(Integer.parseInt(request.getParameter("duracion")));
        reservaDAO.crearReserva(reserva);
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
        ReservaDAO reservaDAO = new ReservaDAO();
        int tiempoRestante = reservaDAO.obtenerTiempoRestante(codCubiculo);
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
