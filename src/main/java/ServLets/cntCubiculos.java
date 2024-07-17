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
import modelo.dto.Cliente;

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
                case "listarClientes":
                    listarClientes(request, response);
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
        int CodLocal = Integer.parseInt(request.getParameter("CodLocal"));
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        List<Cubiculo> cubiculos = cubiculoDAO.obtenerCubiculosPorLocal(CodLocal);
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

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        List<Cliente> clientes = cubiculoDAO.obtenerClientes();
        Gson gson = new Gson();
        String json = gson.toJson(clientes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void crearReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CubiculoDAO cubiculoDAO = new CubiculoDAO();
        Reserva reserva = new Reserva();

        // Verificar y obtener datos de los parámetros del request
        String fechaStr = request.getParameter("fecha");
        String horaInicioStr = request.getParameter("horaInicio");
        String horaFinStr = request.getParameter("horaFin");

        // Convertir y manejar los posibles errores de formato
        try {
            Date fecha = Date.valueOf(fechaStr); // Convertir String a Date
            Timestamp horaInicio = Timestamp.valueOf(horaInicioStr.replace("T", " ") + ":00");
            Timestamp horaFin = Timestamp.valueOf(horaFinStr.replace("T", " ") + ":00");

            reserva.setcodCliente(Integer.parseInt(request.getParameter("codCliente")));
            reserva.setcodEmpleado(1); // Proporciona el ID del empleado actual
            reserva.setcodCubiculo(Integer.parseInt(request.getParameter("codCubiculo")));
            reserva.setFecha(fecha);
            reserva.sethoraInicio(horaInicio);
            reserva.sethoraFin(horaFin);
            reserva.setTiempo(request.getParameter("tiempo"));
            cubiculoDAO.crearReserva(reserva);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha o hora incorrecto");
        }
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
