/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.Contact;
import conexion.ConectaBD;

/**
 *
 * @author ztomz
 */

public class ContactDAO {
    private Connection cnx;

    public ContactDAO() {
        cnx = new ConectaBD().getConnection();
    }
    
    public String insert(Contact c) {
        String resp = "";
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "INSERT INTO contacto (nombre, correo, telefono, mensaje) VALUES(?,?,?,?) ";
        
        try {
            ps = cnx.prepareStatement(cadSQL);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCorreo());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getMensaje());
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            resp = ex.getMessage();
        }
        return resp;
    }
    public Contact get(int idx) {
        Contact c = null;
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "SELECT * FROM contacto where idcontacto=?";
        try {
            ps = cnx.prepareStatement(cadSQL);
            ps.setInt(1, idx);
            rs = ps.executeQuery();
            if  (rs.next()){
                c = new Contact();
                c.setIdcontacto(rs.getInt("idcontacto"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setTelefono(rs.getString("telefono"));
                c.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException ex) {
        }
        return c;
    }
}
