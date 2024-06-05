package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.Contactenos;
import conexion.ConectaBD;

/**
 *
 * Author: ztomz
 */
public class ContactenosDAO {

    private Connection cnx;

    public ContactenosDAO() {
        cnx = new ConectaBD().getConnection();
    }

    public String insert(Contactenos c) {
        String resp = "";
        String cadSQL = "INSERT INTO contacto (nombres_contacto, apellidos_contacto, correo_contacto, mensaje_contacto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = cnx.prepareStatement(cadSQL)) {
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getMensaje());
            ps.executeUpdate();
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }
        return resp;
    }
}
