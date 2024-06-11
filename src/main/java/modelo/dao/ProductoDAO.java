package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Producto;
import conexion.ConectaBD;

public class ProductoDAO {
    private Connection cnx;

    public ProductoDAO() {
        cnx = new ConectaBD().getConnection();
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.Nombre AS NombreCategoria FROM Producto p INNER JOIN Categoria c ON p.CodCategoria = c.CodCategoria";
        
        try (PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}