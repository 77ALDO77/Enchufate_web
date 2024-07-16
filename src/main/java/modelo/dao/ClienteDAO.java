package modelo.dao;

import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public int obtenerCodigoPorDNI(String dni) {
        int codCliente = -1;
        String sql = "SELECT CodCliente FROM Cliente WHERE DNI = ?";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setString(1, dni);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    codCliente = rs.getInt("CodCliente");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codCliente;
    }
}
