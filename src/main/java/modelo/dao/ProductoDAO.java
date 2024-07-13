package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Producto;
import conexion.ConectaBD;
import modelo.dto.Categoria;
import modelo.dto.Proveedor;

public class ProductoDAO {

    private Connection cnx;

    public ProductoDAO() {
        cnx = new ConectaBD().getConnection();
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.Nombre AS NombreCategoria FROM Producto p INNER JOIN Categoria c ON p.CodCategoria = c.CodCategoria";

        try (PreparedStatement ps = cnx.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
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

    public Producto obtenerProductoPorId(int codproducto) {
        Producto producto = null;
        try {
            String query = "SELECT * FROM producto WHERE codproducto = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, codproducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setCodproducto(rs.getInt("codproducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setImagen(rs.getString("imagen"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public List<Producto> getList() {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT p.codproducto, p.nombre, p.descripcion, p.fechavencimiento, p.precio, c.codcategoria, c.nombre as categoria, v.codproveedor, v.nombre as proveedor FROM producto p inner join categoria c on (p.codcategoria = c.codcategoria) inner join proveedor v on (p.codproveedor = v.codproveedor);";
        List<Producto> lista = null;

        try {
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("codproducto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("fechavencimiento"),
                        rs.getDouble("precio"),
                        new Categoria(rs.getInt("codcategoria"), rs.getString("categoria")),
                        new Proveedor(rs.getInt("codproveedor"), rs.getString("proveedor"))
                );
                lista.add(producto);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al cargar los datos del producto en la lista " + ex);
        }
        return lista;
    }
}
