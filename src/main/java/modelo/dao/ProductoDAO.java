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
import javax.swing.JOptionPane;

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
        String sql = "SELECT p.codproducto, p.nombre, p.descripcion, p.fechavencimiento, p.precio, c.codcategoria, c.nombre as categoria, v.codproveedor, v.nombre as proveedor FROM producto p inner join categoria c on (p.codcategoria = c.codcategoria) inner join proveedor v on (p.codproveedor = v.codproveedor) where estado='S' order by codproducto asc;";
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
            System.out.println("Error al listar los productos: " + ex);
        }
        return lista;
    }

    public Producto ObtenerProducto(int cod) {
        Producto p = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql = "select nombre, descripcion, fechavencimiento, precio, codcategoria, codproveedor, codproducto from producto where codproducto=? and estado='S';";

        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFechavencimiento(rs.getString("fechavencimiento"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCategoria(new Categoria(rs.getInt("codcategoria"), ""));
                p.setProveedor(new Proveedor(rs.getInt("codproveedor"), ""));
                p.setCodproducto(rs.getInt("codproducto"));
            } else {
                System.out.println("No se encontró ningún producto con el código: " + cod);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener producto por su código: " + ex);
        }
        return p;
    }

    public String RegistrarProducto(Producto p) {
        String resp = "";
        PreparedStatement ps;
        ResultSet rs;
        String sql = "insert into producto (nombre, descripcion, fechavencimiento, precio, codcategoria, codproveedor, estado) values (?, ?, ?, ?, ?, ?, 'S');";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getFechavencimiento());
            ps.setDouble(4, p.getPrecio());
            if (p.getCodcategoria() != 0) {
                ps.setInt(5, p.getCodcategoria());
            }
            if (p.getCodproveedor() != 0) {
                ps.setInt(6, p.getCodproveedor());
            }
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                resp = "No se ha registrado";
            }
            ps.close();
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }
        return resp;
    }
    
    public String ActualizarProducto(Producto p){
        String resp = "";
        PreparedStatement ps;
        ResultSet rs;
        String sql = "update producto set nombre=?, descripcion=?, fechavencimiento=?, precio=?, codcategoria=?, codproveedor=? where codproducto=?;";
        try{
            ps = cnx.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getFechavencimiento());
            ps.setDouble(4, p.getPrecio());
            if (p.getCodcategoria() != 0) {
                ps.setInt(5, p.getCodcategoria());
            }
            if (p.getCodproveedor() != 0) {
                ps.setInt(6, p.getCodproveedor());
            }
            ps.setInt(7, p.getCodproducto());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                resp = "No se ha actualizado";
            }
            ps.close();
        }catch(SQLException ex){
            resp = ex.getMessage();
        }
        return resp;
    }
}
