package modelo.dao;

import modelo.dto.Cubiculo;
import modelo.dto.NivelCubiculo;
import modelo.dto.Reserva;
import modelo.dto.Locales;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Cliente;

public class CubiculoDAO {

    public List<Cubiculo> obtenerCubiculosPorLocal(int CodLocal) {
        List<Cubiculo> cubiculos = new ArrayList<>();
        String sql = "SELECT * FROM Cubiculo WHERE CodLocal = ? AND estado = 'activo'";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, CodLocal);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Cubiculo cubiculo = new Cubiculo();
                    cubiculo.setcodCubiculo(rs.getInt("codCubiculo"));
                    cubiculo.setCodLocal(rs.getInt("CodLocal"));
                    cubiculo.setcodNivel(rs.getInt("codNivel"));
                    cubiculo.setEstadoCubiculo(rs.getString("Estado_Cubiculo"));
                    cubiculo.setEstado(rs.getString("estado"));
                    cubiculos.add(cubiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cubiculos;
    }

    public List<NivelCubiculo> obtenerNivelesCubiculo() {
        List<NivelCubiculo> niveles = new ArrayList<>();
        String sql = "SELECT * FROM nivelcubiculo";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                NivelCubiculo nivel = new NivelCubiculo();
                nivel.setcodNivel(rs.getInt("codNivel"));
                nivel.setNombre(rs.getString("Nombre"));
                nivel.setPrecio(rs.getDouble("Precio"));
                niveles.add(nivel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return niveles;
    }

    public List<Locales> obtenerLocales() {
        List<Locales> locales = new ArrayList<>();
        String sql = "SELECT * FROM Locales";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Locales local = new Locales();
                local.setCodLocal(rs.getInt("CodLocal"));
                local.setDireccion(rs.getString("Direccion"));
                local.setNombre(rs.getString("Nombre"));
                local.setTelefono(rs.getInt("Telefono"));
                locales.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locales;
    }

    public void crearReserva(Reserva reserva) {
        String sqlReserva = "INSERT INTO Reserva (CodCliente, CodEmpleado, CodCubiculo, Fecha, HoraInicio, HoraFin, Tiempo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlEstadoCubiculo = "UPDATE Cubiculo SET Estado_Cubiculo = 'Ocupado' WHERE CodCubiculo = ?";

        try (Connection cnx = new ConectaBD().getConnection(); 
             PreparedStatement pstReserva = cnx.prepareStatement(sqlReserva); 
             PreparedStatement pstEstadoCubiculo = cnx.prepareStatement(sqlEstadoCubiculo)) {
            // Insertar nueva reserva
            pstReserva.setInt(1, reserva.getcodCliente());
            pstReserva.setInt(2, reserva.getcodEmpleado());
            pstReserva.setInt(3, reserva.getcodCubiculo());
            pstReserva.setDate(4, reserva.getFecha());
            pstReserva.setTimestamp(5, reserva.gethoraInicio());
            pstReserva.setTimestamp(6, reserva.gethoraFin());
            pstReserva.setString(7, reserva.getTiempo());
            pstReserva.executeUpdate();

            // Actualizar estado del cubículo
            pstEstadoCubiculo.setInt(1, reserva.getcodCubiculo());
            pstEstadoCubiculo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> obtenerReservasPorCubiculo(int codCubiculo) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva WHERE CodCubiculo = ? AND Fecha = CURRENT_DATE ORDER BY HoraInicio DESC";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setcodReserva(rs.getInt("CodReserva"));
                    reserva.setcodCliente(rs.getInt("CodCliente"));
                    reserva.setcodEmpleado(rs.getInt("CodEmpleado"));
                    reserva.setcodCubiculo(rs.getInt("CodCubiculo"));
                    reserva.setFecha(rs.getDate("Fecha"));
                    reserva.sethoraInicio(rs.getTimestamp("HoraInicio"));
                    reserva.sethoraFin(rs.getTimestamp("HoraFin"));
                    reserva.setTiempo(rs.getString("Tiempo"));
                    reservas.add(reserva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public Cubiculo obtenerCubiculoPorCodigo(int codCubiculo) {
        Cubiculo cubiculo = null;
        String sql = "SELECT * FROM Cubiculo WHERE CodCubiculo = ?";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    cubiculo = new Cubiculo();
                    cubiculo.setcodCubiculo(rs.getInt("codCubiculo"));
                    cubiculo.setCodLocal(rs.getInt("CodLocal"));
                    cubiculo.setcodNivel(rs.getInt("codNivel"));
                    cubiculo.setEstadoCubiculo(rs.getString("Estado_Cubiculo"));
                    cubiculo.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cubiculo;
    }

    public int obtenerTiempoRestante(int codCubiculo) {
        int tiempoRestante = 0;
        String sql = "SELECT HoraFin FROM Reserva WHERE CodCubiculo = ? AND "
                + "Fecha = CURRENT_DATE ORDER BY HoraFin DESC LIMIT 1";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Timestamp horaFin = rs.getTimestamp("horaFin");
                    Timestamp horaActual = new Timestamp(System.currentTimeMillis());
                    tiempoRestante = (int) (horaFin.getTime() - horaActual.getTime()) / (1000 * 60);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiempoRestante;
    }
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("CodCliente"));
                cliente.setNombres(rs.getString("Nombres"));
                cliente.setApePaterno(rs.getString("ApePaterno"));
                cliente.setApeMaterno(rs.getString("ApeMaterno"));
                cliente.setDNI(rs.getString("DNI"));
                cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                cliente.setUsuario(rs.getString("Usuario"));
                cliente.setCorreo(rs.getString("Correo"));
                cliente.setContraseña(rs.getString("Contraseña"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    

}
