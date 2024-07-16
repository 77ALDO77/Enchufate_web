package modelo.dao;

import modelo.dto.Cubiculo;
import modelo.dto.Local;
import conexion.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    cubiculo.setNivel(rs.getString("Nivel"));
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

    public List<Local> obtenerLocales() {
        List<Local> locales = new ArrayList<>();
        String sql = "SELECT * FROM Locales WHERE estado = 'activo'";

        try (Connection cnx = new ConectaBD().getConnection(); PreparedStatement pst = cnx.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Local local = new Local();
                local.setCodLocal(rs.getInt("CodLocal"));
                local.setDireccion(rs.getString("Direccion"));
                local.setNombre(rs.getString("Nombre"));
                local.setTelefono(rs.getString("Telefono"));
                local.setEstado(rs.getString("estado"));
                locales.add(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locales;
    }

    public void actualizarEstadoCubiculo(int codCubiculo, String nuevoEstado) {
        String sql = "UPDATE Cubiculo SET Estado_Cubiculo = ? WHERE CodCubiculo = ?";

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
                    cubiculo.setCodLocal(rs.getInt("CodLocal"));
                    cubiculo.setNivel(rs.getString("Nivel"));
                    cubiculo.setEstadoCubiculo(rs.getString("Estado_Cubiculo"));
                    cubiculo.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cubiculo;
    }
}
