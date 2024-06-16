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

    // Método para obtener el precio de un producto
    public double obtenerPrecioProducto(int codProducto) {
        double precio = 0.0;
        String sql = "SELECT Precio FROM Producto WHERE CodProducto = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, codProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    precio = rs.getDouble("Precio");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return precio;
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(int codCliente, int codProducto, int cantidad) {
        double precioProducto = obtenerPrecioProducto(codProducto);
        double total = precioProducto * cantidad;

        String sqlSelect = "SELECT Cantidad, Total FROM Carrito WHERE CodCliente = ? AND CodProducto = ?";
        String sqlInsert = "INSERT INTO Carrito (CodCliente, CodProducto, Cantidad, Total) VALUES (?, ?, ?, ?)";
        String sqlUpdate = "UPDATE Carrito SET Cantidad = ?, Total = ? WHERE CodCliente = ? AND CodProducto = ?";

        try (PreparedStatement psSelect = cnx.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, codCliente);
            psSelect.setInt(2, codProducto);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    // Producto ya existe en el carrito
                    int existingCantidad = rs.getInt("Cantidad");
                    int newCantidad = existingCantidad + cantidad;
                    double newTotal = precioProducto * newCantidad;

                    try (PreparedStatement psUpdate = cnx.prepareStatement(sqlUpdate)) {
                        psUpdate.setInt(1, newCantidad);
                        psUpdate.setDouble(2, newTotal);
                        psUpdate.setInt(3, codCliente);
                        psUpdate.setInt(4, codProducto);
                        psUpdate.executeUpdate();
                    }
                } else {
                    // Producto no existe en el carrito, insertar como nuevo
                    try (PreparedStatement psInsert = cnx.prepareStatement(sqlInsert)) {
                        psInsert.setInt(1, codCliente);
                        psInsert.setInt(2, codProducto);
                        psInsert.setInt(3, cantidad);
                        psInsert.setDouble(4, total);
                        psInsert.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para actualizar la cantidad de un producto en el carrito
    public void actualizarCantidad(int codCliente, int codProducto, String action) {
        double precioProducto = obtenerPrecioProducto(codProducto);

        String sqlSelect = "SELECT Cantidad FROM Carrito WHERE CodCliente = ? AND CodProducto = ?";
        String sqlUpdate = "UPDATE Carrito SET Cantidad = ?, Total = ? WHERE CodCliente = ? AND CodProducto = ?";

        try (PreparedStatement psSelect = cnx.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, codCliente);
            psSelect.setInt(2, codProducto);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    int cantidad = rs.getInt("Cantidad");
                    if ("increase".equals(action)) {
                        cantidad++;
                    } else if ("decrease".equals(action) && cantidad > 1) {
                        cantidad--;
                    }

                    double total = precioProducto * cantidad;

                    try (PreparedStatement psUpdate = cnx.prepareStatement(sqlUpdate)) {
                        psUpdate.setInt(1, cantidad);
                        psUpdate.setDouble(2, total);
                        psUpdate.setInt(3, codCliente);
                        psUpdate.setInt(4, codProducto);
                        psUpdate.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para eliminar un producto del carrito
    public void eliminarProductoDelCarrito(int codCliente, int codProducto) {
        String sql = "DELETE FROM Carrito WHERE CodCliente = ? AND CodProducto = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            ps.setInt(2, codProducto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obtener los productos en el carrito
    public List<Producto> getProductosEnCarrito(int codCliente) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, cat.Nombre AS NombreCategoria, c.Cantidad, c.Total "
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
                    producto.setTotal(rs.getDouble("Total"));
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para obtener la conexión a la base de datos (si es necesario)
    public Connection getConnection() {
        return this.cnx;
    }
}

