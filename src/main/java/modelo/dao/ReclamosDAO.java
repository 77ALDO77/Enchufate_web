package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.Reclamos;
import conexion.ConectaBD;

public class ReclamosDAO {

    private Connection cnx;

    public ReclamosDAO() {
        cnx = new ConectaBD().getConnection();
    }

    public String insert(Reclamos c) {
        String resp = "";
        String cadSQL = "INSERT INTO reclamos (NombreCli, CorreoReclamo, dniReclamo, fechaReclamo, AsuntoReclamo, ContenidoReclamo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = cnx.prepareStatement(cadSQL)) {
            ps.setString(1, c.getNombre_cliente());
            ps.setString(2, c.getCorreo_reclamo());
            ps.setString(3, c.getDni_reclamo());
            ps.setDate(4, java.sql.Date.valueOf(c.getFecha_reclamo()));
            ps.setString(5, c.getAsunto_reclamo());
            ps.setString(6, c.getContenido_reclamo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }

        return resp;
    }
}
