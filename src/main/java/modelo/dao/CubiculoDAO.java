package modelo.dao;

import conexion.ConectaBD;
import modelo.dto.Cubiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CubiculoDAO {
    public ArrayList<Cubiculo> obtenerCubiculos() {
        ArrayList<Cubiculo> cubiculos = new ArrayList<>();
        ConectaBD conectaBD = new ConectaBD();
        Connection connection = conectaBD.getConnection();

        try {
            String sqlCubiculos = "SELECT * FROM Cubiculo";
            PreparedStatement pstCubiculos = connection.prepareStatement(sqlCubiculos);
            ResultSet rsCubiculos = pstCubiculos.executeQuery();

            while (rsCubiculos.next()) {
                Cubiculo cubiculo = new Cubiculo();
                cubiculo.setCodCubiculo(rsCubiculos.getInt("CodCubiculo"));
                cubiculo.setNivel(rsCubiculos.getInt("Nivel"));
                cubiculo.setEstado(rsCubiculos.getString("Estado"));
                cubiculos.add(cubiculo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cubiculos;
    }

    public int obtenerCubiculosActivos() {
        int cubiculosActivos = 0;
        ConectaBD conectaBD = new ConectaBD();
        Connection connection = conectaBD.getConnection();

        try {
            String sqlCubiculosActivos = "SELECT COUNT(*) FROM Cubiculo WHERE Estado = 'Ocupado'";
            PreparedStatement pstCubiculosActivos = connection.prepareStatement(sqlCubiculosActivos);
            ResultSet rsCubiculosActivos = pstCubiculosActivos.executeQuery();

            if (rsCubiculosActivos.next()) {
                cubiculosActivos = rsCubiculosActivos.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cubiculosActivos;
    }

    public int obtenerReservasHoy() {
        int reservasHoy = 0;
        ConectaBD conectaBD = new ConectaBD();
        Connection connection = conectaBD.getConnection();

        try {
            String sqlReservasHoy = "SELECT COUNT(*) FROM Reserva WHERE Fecha = CURDATE()";
            PreparedStatement pstReservasHoy = connection.prepareStatement(sqlReservasHoy);
            ResultSet rsReservasHoy = pstReservasHoy.executeQuery();

            if (rsReservasHoy.next()) {
                reservasHoy = rsReservasHoy.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return reservasHoy;
    }
}
