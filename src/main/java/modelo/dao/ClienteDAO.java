package modelo.dao;

import modelo.dto.Cliente;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public Cliente obtenerClientePorDNI(String dni) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE DNI = ?";
        
        try (Connection cnx = new ConectaBD().getConnection();
             PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setString(1, dni);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("CodCliente"));
                    cliente.setNombres(rs.getString("Nombres"));
                    cliente.setApePaterno(rs.getString("ApePaterno"));
                    cliente.setApeMaterno(rs.getString("ApeMaterno"));
                    cliente.setDNI(rs.getString("DNI"));
                    cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                    cliente.setUsuario(rs.getString("Usuario"));
                    cliente.setCorreo(rs.getString("Correo"));
                    cliente.setContraseña(rs.getString("Contraseña"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
