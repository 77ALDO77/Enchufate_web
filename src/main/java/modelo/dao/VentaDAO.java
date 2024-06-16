package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Venta;
import conexion.ConectaBD;

public class VentaDAO {

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT Fecha, SUM(Cantidad) as Total FROM DetalleCompra INNER JOIN Compra ON DetalleCompra.CodCompra = Compra.CodCompra GROUP BY Fecha";
        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setFecha(rs.getDate("Fecha"));
                venta.setTotal(rs.getInt("Total"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
