package modelo.dao;

import modelo.dto.Empleado;
import conexion.ConectaBD;

import java.sql.*;


public class EmpleadoDAO {
    public Empleado authenticate(String correo, String clave) {
        Empleado empleado = null;
        Connection cnx = new ConectaBD().getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM empleado WHERE correo = ? AND clave = ?";

        try {
            ps = cnx.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setCodEmpleado(rs.getInt("CodEmpleado"));
                empleado.setCodLocal(rs.getInt("CodLocal"));
                empleado.setCodArea(rs.getInt("CodArea"));
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setApellidos(rs.getString("Apellidos"));
                empleado.setFechaNacimiento("FechaNacimineto");
                empleado.setDni(rs.getInt("DNI"));
                empleado.setSexo(rs.getString("Sexo"));
                empleado.setCelular(rs.getInt("Celular"));
                empleado.setCorreo(rs.getString("Correo"));
                empleado.setSalario(rs.getInt("Salario"));
                empleado.setClave(rs.getString("Clave"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return empleado;
    }
}
