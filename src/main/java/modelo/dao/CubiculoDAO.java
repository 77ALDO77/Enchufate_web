package modelo.dao;

import modelo.dto.Cubiculo;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CubiculoDAO {

    public List<Cubiculo> obtenerCubiculos() {
        List<Cubiculo> cubiculos = new ArrayList<>();
        String sql = "SELECT * FROM Cubiculo";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cubiculo cubiculo = new Cubiculo();
                cubiculo.setCodCubiculo(rs.getInt("CodCubiculo"));
                cubiculo.setNivel(rs.getInt("Nivel"));
                cubiculo.setEstado(rs.getString("Estado"));
                cubiculos.add(cubiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cubiculos;
    }

    public void actualizarEstadoCubiculo(int codCubiculo, String nuevoEstado) {
        String sql = "UPDATE Cubiculo SET Estado = ? WHERE CodCubiculo = ?";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql)) {

            pst.setString(1, nuevoEstado);
            pst.setInt(2, codCubiculo);
            pst.executeUpdate();
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
                    cubiculo.setNivel(rs.getInt("Nivel"));
                    cubiculo.setEstado(rs.getString("Estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cubiculo;
    }
}
