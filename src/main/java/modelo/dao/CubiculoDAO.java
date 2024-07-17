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

public class CubiculoDAO {

    public List<Cubiculo> obtenerCubiculosPorLocal(int codLocal) {
        List<Cubiculo> cubiculos = new ArrayList<>();
        String sql = "SELECT * FROM Cubiculo WHERE CodLocal = ? AND estado = 'activo'";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codLocal);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Cubiculo cubiculo = new Cubiculo();
                    cubiculo.setCodCubiculo(rs.getInt("CodCubiculo"));
                    cubiculo.setCodLocal(rs.getInt("CodLocal"));
                    cubiculo.setCodNivel(rs.getInt("CodNivel"));
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
        String sql = "SELECT * FROM NivelCubiculo";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                NivelCubiculo nivel = new NivelCubiculo();
                nivel.setCodNivel(rs.getInt("CodNivel"));
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

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pstReserva = cnx.prepareStatement(sqlReserva); PreparedStatement pstEstadoCubiculo = cnx.prepareStatement(sqlEstadoCubiculo)) {
            // Insertar nueva reserva
            pstReserva.setInt(1, reserva.getCodCliente());
            pstReserva.setInt(2, reserva.getCodEmpleado());
            pstReserva.setInt(3, reserva.getCodCubiculo());
            pstReserva.setDate(4, reserva.getFecha());
            pstReserva.setTimestamp(5, reserva.getHoraInicio());
            pstReserva.setTimestamp(6, reserva.getHoraFin());
            pstReserva.setString(7, reserva.getTiempo());
            pstReserva.executeUpdate();

            // Actualizar estado del cubículo
            pstEstadoCubiculo.setInt(1, reserva.getCodCubiculo());
            pstEstadoCubiculo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cubiculo obtenerCubiculoPorCodigo(int codCubiculo) {
        Cubiculo cubiculo = null;
        String sql = "SELECT * FROM Cubiculo WHERE CodCubiculo = ?";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    cubiculo = new Cubiculo();
                    cubiculo.setCodCubiculo(rs.getInt("CodCubiculo"));
                    cubiculo.setCodLocal(rs.getInt("CodLocal"));
                    cubiculo.setCodNivel(rs.getInt("CodNivel"));
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
        String sql = "SELECT HoraFin FROM Reserva WHERE CodCubiculo = ? AND Fecha = CURRENT_DATE ORDER BY HoraFin DESC LIMIT 1";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Timestamp horaFin = rs.getTimestamp("HoraFin");
                    Timestamp horaActual = new Timestamp(System.currentTimeMillis());
                    tiempoRestante = (int) (horaFin.getTime() - horaActual.getTime()) / (1000 * 60);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiempoRestante;
    }
}
