/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Producto;
import conexion.ConectaBD;

public class CarritoDAO {
    private Connection cnx;

    public CarritoDAO() {
        cnx = new ConectaBD().getConnection();
    }

    public void agregarProducto(int codCliente, int codProducto, int cantidad) {
        String sql = "INSERT INTO Carrito (CodCliente, CodProducto, Cantidad) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE Cantidad = Cantidad + VALUES(Cantidad)";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            ps.setInt(2, codProducto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Producto> getProductosEnCarrito(int codCliente) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, cat.Nombre AS NombreCategoria, c.Cantidad "
                   + "FROM Producto p "
                   + "JOIN Carrito c ON p.CodProducto = c.CodProducto "
                   + "JOIN Categoria cat ON p.CodCategoria = cat.CodCategoria "
                   + "WHERE c.CodCliente = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setCodproducto(rs.getInt("CodProducto"));
                    producto.setCodcategoria(rs.getInt("CodCategoria"));
                    producto.setCodproveedor(rs.getInt("CodProveedor"));
                    producto.setPrecio(rs.getDouble("Precio"));
                    producto.setNombre(rs.getString("Nombre"));
                    producto.setDescripcion(rs.getString("Descripcion"));
                    producto.setImagen(rs.getString("Imagen"));
                    producto.setFechavencimiento(rs.getString("FechaVencimiento"));
                    producto.setNombreCategoria(rs.getString("NombreCategoria"));
                    producto.setCantidad(rs.getInt("Cantidad"));
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}




