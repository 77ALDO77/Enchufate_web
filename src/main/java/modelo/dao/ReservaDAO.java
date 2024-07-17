package modelo.dao;

import modelo.dto.Reserva;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

     public List<Reserva> obtenerReservas(String fechaInicio, String fechaFin) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT Fecha, COUNT(*) as Total FROM Reserva WHERE Fecha BETWEEN ? AND ? GROUP BY Fecha";
        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {
            pst.setString(1, fechaInicio);
            pst.setString(2, fechaFin);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setFecha(rs.getDate("Fecha"));
                    reserva.settotal(rs.getInt("Total"));
                    reservas.add(reserva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}
