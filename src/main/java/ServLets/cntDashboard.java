/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import modelo.dao.VentaDAO;
import modelo.dto.Venta;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import modelo.dao.ReservaDAO;
import modelo.dto.Reserva;

/**
 *
 * @author aldom
 */
public class cntDashboard extends HttpServlet {

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
            switch (accion) {
                case "cargarVentas":
                    cargarVentas(request, response);
                    break;
                case "cargarReservas":
                    cargarReservas(request, response);
                    break;
                case "exportarExcelVentas":
                    exportarExcelVentas(request, response);
                    break;
                case "exportarExcelReservas":
                    exportarExcelReservas(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no proporcionada");
        }
    }

    private void cargarVentas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        VentaDAO ventaDAO = new VentaDAO();
        List<Venta> ventas = ventaDAO.obtenerVentas(fechaInicio, fechaFin);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json = gson.toJson(ventas);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void cargarReservas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> reservas = reservaDAO.obtenerReservas(fechaInicio, fechaFin);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json = gson.toJson(reservas);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void exportarExcelVentas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        VentaDAO ventaDAO = new VentaDAO();
        List<Venta> ventas = ventaDAO.obtenerVentas(fechaInicio, fechaFin);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Ventas Diarias");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Fecha");
        headerRow.createCell(1).setCellValue("Total Ventas");

        int rowNum = 1;
        for (Venta venta : ventas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(venta.getFecha().toString());
            row.createCell(1).setCellValue(venta.getTotal());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ventas_diarias.xlsx");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void exportarExcelReservas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> reservas = reservaDAO.obtenerReservas(fechaInicio, fechaFin);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservas Diarias");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Fecha");
        headerRow.createCell(1).setCellValue("Total Reservas");

        int rowNum = 1;
        for (Reserva reserva : reservas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reserva.getFecha().toString());
            row.createCell(1).setCellValue(reserva.getTotal());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=reservas_diarias.xlsx");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
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
