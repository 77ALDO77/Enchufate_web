package modelo.dao;

import modelo.dto.Reserva;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public void crearReserva(Reserva reserva) {
    String sqlReserva = "INSERT INTO Reserva (CodCliente, CodEmpleado, CodCubiculo, Fecha, Hora, Duracion) VALUES (?, ?, ?, ?, ?, ?)";
    String sqlEstadoCubiculo = "UPDATE Cubiculo SET Estado = 'Ocupado' WHERE CodCubiculo = ?";

    try (Connection cnx = new ConectaBD().getConnection();
         PreparedStatement pstReserva = cnx.prepareStatement(sqlReserva);
         PreparedStatement pstEstadoCubiculo = cnx.prepareStatement(sqlEstadoCubiculo)) {

        // Insertar nueva reserva
        pstReserva.setInt(1, reserva.getCodCliente());
        pstReserva.setInt(2, reserva.getCodEmpleado());
        pstReserva.setInt(3, reserva.getCodCubiculo());
        pstReserva.setDate(4, reserva.getFecha());
        pstReserva.setTime(5, reserva.getHora());
        pstReserva.setInt(6, reserva.getDuracion());
        pstReserva.executeUpdate();

        // Actualizar estado del cubículo
        pstEstadoCubiculo.setInt(1, reserva.getCodCubiculo());
        pstEstadoCubiculo.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public int obtenerTiempoRestante(int codCubiculo) {
        int tiempoRestante = 0;
        String sql = "SELECT Hora, Duracion FROM Reserva WHERE CodCubiculo = ? AND Fecha = CURRENT_DATE ORDER BY Hora DESC LIMIT 1";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setInt(1, codCubiculo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Time horaInicio = rs.getTime("Hora");
                    int duracion = rs.getInt("Duracion");
                    LocalTime horaFin = horaInicio.toLocalTime().plusMinutes(duracion);
                    LocalTime horaActual = LocalTime.now();
                    tiempoRestante = (int) java.time.Duration.between(horaActual, horaFin).toMinutes();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiempoRestante;
    }
}
