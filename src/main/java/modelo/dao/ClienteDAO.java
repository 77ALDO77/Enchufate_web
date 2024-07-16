package modelo.dao;

import modelo.dto.Cliente;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author Benja
 */
public class ClienteDAO {

    public Cliente obtenerClientePorDNI(String dni) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE DNI = ?";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
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

    public ArrayList<Cliente> mostrarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "select * from Cliente;";
            PreparedStatement pre = cnx.prepareStatement(sql);
            ResultSet rs;
            rs = pre.executeQuery();
            while (rs.next()) {

                // OBTENER LOS DATOS DE CADA Cliente
                int clienteIdBD = rs.getInt("CodCliente");
                String nombresBD = rs.getString("Nombres");
                String apellidoPaternoBD = rs.getString("ApePaterno");
                String apellidoMaternoBD = rs.getString("ApeMaterno");
                String NdocumentoBD = rs.getString("DNI");
                Date fechaNacimientoBD = rs.getDate("FechaNacimiento");
                String usuarioBD = rs.getString("Usuario");
                String correoBD = rs.getString("Correo");
                String contrasenaBD = rs.getString("Contraseña");

                Cliente c = new Cliente();
                // AGREGAR LOS DATOS OBTENIDOS DE LA BASE DE DATOS A LOS SETTERS DEL CLIENTE
                c.setCodCliente(clienteIdBD);
                c.setNombres(nombresBD);
                c.setApePaterno(apellidoPaternoBD);
                c.setApeMaterno(apellidoMaternoBD);
                c.setDNI(NdocumentoBD);
                c.setFechaNacimiento(fechaNacimientoBD);
                c.setUsuario(usuarioBD);
                c.setCorreo(correoBD);
                c.setContraseña(contrasenaBD);

                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar " + e.getMessage());
        } finally {
//            this.desconectar();
        }
        return lista;
    }

    public int insertarCliente(Cliente c) {
        int res = 0;
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "INSERT INTO Cliente (Nombres, ApePaterno, ApeMaterno, DNI, FechaNacimiento, Usuario, Correo, Contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setString(1, c.getNombres());
            pre.setString(2, c.getApePaterno());
            pre.setString(3, c.getApeMaterno());
            pre.setString(4, c.getDNI());
            pre.setDate(5, c.getFechaNacimiento());
            pre.setString(6, c.getUsuario());
            pre.setString(7, c.getCorreo());
            pre.setString(8, c.getContraseña());
            res = pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar " + e.getMessage());
        } finally {
//            this.desconectar();
        }

        return res;
    }

    public int modificarCliente(Cliente c){
        int res = 0;
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "UPDATE Cliente SET Nombres = ?, ApePaterno = ?, ApeMaterno = ?, DNI = ?, FechaNacimiento = ?, Usuario = ?, Correo = ?, Contraseña = ? WHERE CodCliente = ?";
            PreparedStatement pre = cnx.prepareStatement(sql);
                        
            pre.setString(1, c.getNombres());
            pre.setString(2, c.getApePaterno());
            pre.setString(3, c.getApeMaterno());
            pre.setString(4, c.getDNI());
            pre.setDate(5, c.getFechaNacimiento());
            pre.setString(6, c.getUsuario());
            pre.setString(7, c.getCorreo());
            pre.setString(8, c.getContraseña());
            pre.setInt(9,c.getCodCliente());
            
            
            res = pre.executeUpdate();            
        } catch (SQLException e) {
            System.out.println("Error al modificar "+e.getMessage());
        } finally {
//            this.desconectar();
        }
        return res;
    }
    
    public int eliminarCliente(Cliente c){
        int res = 0;
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "delete from Cliente where Cliente.CodCliente=?";
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setInt(1, c.getCodCliente());
            
            res = pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar "+e.getMessage());
        } finally {
//            this.desconectar();
        }
        return res;
    }
    
    
}
