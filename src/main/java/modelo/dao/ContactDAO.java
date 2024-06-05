package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.Contact;
import conexion.ConectaBD;

/**
 *
 * Author: ztomz
 */

public class ContactDAO {
    private Connection cnx;

    public ContactDAO() {
        cnx = new ConectaBD().getConnection();
    }
    
    public String insert(Contact c) {
        String resp = "";
        PreparedStatement ps;
        String cadSQL = "INSERT INTO Contacto (nombres_contacto, apellidos_contacto, correo_contacto, mensaje_contacto) VALUES (?, ?, ?, ?)";
        
        try {
            ps = cnx.prepareStatement(cadSQL);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getMensaje());
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }
        return resp;
    }

    public Contact get(int id) {
        Contact c = null;
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "SELECT * FROM Contacto WHERE id_contacto = ?";
        try {
            ps = cnx.prepareStatement(cadSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if  (rs.next()){
                c = new Contact();
                c.setIdcontacto(rs.getInt("id_contacto"));
                c.setNombre(rs.getString("nombres_contacto"));
                c.setApellidos(rs.getString("apellidos_contacto"));
                c.setCorreo(rs.getString("correo_contacto"));
                c.setMensaje(rs.getString("mensaje_contacto"));
            }
        } catch (SQLException ex) {
            // Handle exception
        }
        return c;
    }
}
