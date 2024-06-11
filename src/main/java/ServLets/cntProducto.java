/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServLets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ProductoDAO;
import modelo.dto.Producto;

@WebServlet("/productos")
public class cntProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoDAO productoDAO;

    public void init() {
        productoDAO = new ProductoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> listaProductos = productoDAO.listarProductos();
        System.out.println("Número de productos en Servlet: " + listaProductos.size()); // Depuración
        List<String> ordenCategorias = new ArrayList<>();
        ordenCategorias.add("Entradas");
        ordenCategorias.add("Segundos");
        ordenCategorias.add("Postres");
        ordenCategorias.add("Piqueos y Snacks");
        ordenCategorias.add("Bebidas no alcoholicas");
        ordenCategorias.add("Cuidado Personal");
        ordenCategorias.add("Cigarros y Vapes");

        // Agrupar productos por categoría en el orden especificado
        Map<String, List<Producto>> productosPorCategoria = new LinkedHashMap<>();
        for (String categoria : ordenCategorias) {
            productosPorCategoria.put(categoria, new ArrayList<>());
        }
        for (Producto producto : listaProductos) {
            if (productosPorCategoria.containsKey(producto.getNombreCategoria())) {
                productosPorCategoria.get(producto.getNombreCategoria()).add(producto);
            }
        }

        request.setAttribute("productosPorCategoria", productosPorCategoria);
        request.setAttribute("ordenCategorias", ordenCategorias);

        RequestDispatcher dispatcher = request.getRequestDispatcher("market.jsp");
        dispatcher.forward(request, response);
    }
}
