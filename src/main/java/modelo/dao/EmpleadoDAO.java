package modelo.dao;

import modelo.dto.Empleado;
import conexion.ConectaBD;

import java.sql.*;
import java.util.ArrayList;
import modelo.dto.Area;
import modelo.dto.Locales;


public class EmpleadoDAO
{
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
    
    public int insertarEmpleado(Empleado em) {
        int res = 0;
        Connection cnx = null;
        PreparedStatement pre = null;

        try {
            cnx = new ConectaBD().getConnection();
            // Insertar en RegistroAlojamiento
            String sql = "INSERT INTO Empleado (CodLocal, CodArea, Nombre, Apellidos, FechaNacimiento, DNI, Sexo, Celular, Salario, Correo, Clave) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pre = cnx.prepareStatement(sql);
            pre.setInt(1, em.getCodLocal());
            pre.setInt(2, em.getCodArea());
            pre.setString(3, em.getNombre());
            pre.setString(4, em.getApellidos());
            pre.setString(5, em.getFechaNacimiento());
            pre.setInt(6, em.getDni());
            pre.setString(7, em.getSexo());
            pre.setInt(8, em.getCelular());
            pre.setDouble(9, em.getSalario());
            pre.setString(10, em.getCorreo());
            pre.setString(11, em.getClave());
            res = pre.executeUpdate();
            System.out.println("Empleado insertado con éxito. Resultado: " + res);

        } catch (SQLException e) {
            System.out.println("Error al insertar " + e.getMessage());
        } finally {
            try {
                if (pre != null) pre.close();
                if (cnx != null) cnx.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return res;
    }
        
    public ArrayList<Empleado> mostrarLocales()
    {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {Connection cnx = new ConectaBD().getConnection();

            String sql = "select * from locales;";

            PreparedStatement pre = cnx.prepareStatement(sql);
            ResultSet rs;
            rs = pre.executeQuery();
            while (rs.next()) {

                // OBTENER LOS DATOS DE CADA LOCAL
                int codLocalBD = rs.getInt("CodLocal");
                String nombresBD = rs.getString("Nombre");
                String direccionBD = rs.getString("Direccion");
                int telefonoBD = rs.getInt("Telefono");
                
                Locales loc = new Locales();

                loc.setCodLocal(codLocalBD);
                loc.setNombre(nombresBD);
                loc.setDireccion(direccionBD);
                loc.setTelefono(telefonoBD);

                Empleado em = new Empleado(loc);

                lista.add(em);

            }
        } catch (SQLException e) {System.out.println("Error al mostrar " + e.getMessage());
        } finally {//            this.desconectar();
        }
        return lista;
    }
        
    public ArrayList<Empleado> mostrarAreas()
    {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {Connection cnx = new ConectaBD().getConnection();

            String sql = "select * from area;";

            PreparedStatement pre = cnx.prepareStatement(sql);
            ResultSet rs;
            rs = pre.executeQuery();
            while (rs.next()) {

                // OBTENER LOS DATOS DE CADA AREA
                int codAreaBD = rs.getInt("CodArea");
                String descripcionBD = rs.getString("Descripcion");
                
                Area ar = new Area();

                ar.setCodArea(codAreaBD);
                ar.setDescripcion(descripcionBD);

                Empleado em = new Empleado(ar);

                lista.add(em);

            }
        } catch (SQLException e) {System.out.println("Error al mostrar " + e.getMessage());
        } finally {//            this.desconectar();
        }
        return lista;
    }
    
    public ArrayList<Empleado> mostrarEmpleados()
    {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {Connection cnx = new ConectaBD().getConnection();

            String sql = "SELECT * FROM Empleado INNER JOIN Locales ON Empleado.CodLocal = Locales.CodLocal INNER JOIN Area ON Empleado.CodArea = Area.CodArea;";

            PreparedStatement pre = cnx.prepareStatement(sql);
            ResultSet rs;
            rs = pre.executeQuery();
            while (rs.next()) {
                
                
                // OBTENER LOS DATOS DE CADA LOCAL
                int codLocalBD = rs.getInt("Locales.CodLocal");
                String nombresBD = rs.getString("Locales.Nombre");
                String direccionBD = rs.getString("Locales.Direccion");
                int telefonoBD = rs.getInt("Locales.Telefono");                
                Locales loc = new Locales();
                loc.setCodLocal(codLocalBD);
                loc.setNombre(nombresBD);
                loc.setDireccion(direccionBD);
                loc.setTelefono(telefonoBD);
                
                // OBTENER LOS DATOS DE CADA AREA
                int codAreaBD = rs.getInt("Area.CodArea");
                String descripcionBD = rs.getString("Area.Descripcion");                
                Area ar = new Area();
                ar.setCodArea(codAreaBD);
                ar.setDescripcion(descripcionBD);
                
                // OBTENER LOS DATOS DE CADA EMPLEADO
                int codEmpleadoBD = rs.getInt("Empleado.CodEmpleado");
                String nombreEmBD = rs.getString("Empleado.Nombre");
                String apellidoEmBD = rs.getString("Empleado.Apellidos");
                String fechaNEmBD = rs.getString("Empleado.FechaNacimiento");
                int dniEmBD = rs.getInt("Empleado.DNI");
                String sexoEmBD = rs.getString("Empleado.Sexo");
                int celularEmBD = rs.getInt("Empleado.Celular");
                double salarioEmBD = rs.getDouble("Empleado.Salario");
                String correoEmBD = rs.getString("Empleado.Correo");
                String claveEmBD = rs.getString("Empleado.Clave");
                
                // ENVIAR LAS CLASES LOCALES Y AREA COMO PARAMETROS DE EMPLEADO
                Empleado em = new Empleado(loc,ar);
                
                em.setCodEmpleado(codEmpleadoBD);
                em.setCodLocal(codLocalBD);
                em.setCodArea(codAreaBD);
                em.setNombre(nombreEmBD);
                em.setApellidos(apellidoEmBD);
                em.setFechaNacimiento(fechaNEmBD);
                em.setDni(dniEmBD);
                em.setSexo(sexoEmBD);
                em.setCelular(celularEmBD);
                em.setSalario(salarioEmBD);
                em.setCorreo(correoEmBD);
                em.setClave(claveEmBD);

                lista.add(em);

            }
        } catch (SQLException e) {System.out.println("Error al mostrar " + e.getMessage());
        } finally {//            this.desconectar();
        }
        return lista;
    }
        
    public int modificarEmpleado(Empleado em)
    {
        int res = 0;
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "UPDATE Empleado SET CodLocal = ?, CodArea = ?, Nombre = ?, Apellidos = ?, FechaNacimiento = ?, DNI = ?, Sexo = ?, Celular = ?, Salario = ?, Correo = ?, Clave = ? WHERE CodEmpleado = ?;";
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setInt(1, em.getCodLocal());
            pre.setInt(2, em.getCodArea());
            pre.setString(3, em.getNombre());
            pre.setString(4, em.getApellidos());
            pre.setString(5, em.getFechaNacimiento());
            pre.setInt(6, em.getDni());
            pre.setString(7, em.getSexo());
            pre.setInt(8, em.getCelular());
            pre.setDouble(9, em.getSalario());
            pre.setString(10, em.getCorreo());
            pre.setString(11, em.getClave());
            pre.setInt(12, em.getCodEmpleado());

            res = pre.executeUpdate();


        } catch (SQLException e) {System.out.println("Error al modificar " + e.getMessage());}
        finally {//            this.desconectar();
        }
        return res;
    }
    
    public int eliminarEmpleado(Empleado em)
    {
        int res = 0;
        try {
            Connection cnx = new ConectaBD().getConnection();
            String sql = "DELETE FROM Empleado WHERE CodEmpleado = ?;";
            PreparedStatement pre = cnx.prepareStatement(sql);

            pre.setInt(1, em.getCodEmpleado());

            res = pre.executeUpdate();
        } catch (SQLException e) {System.out.println("Error al eliminar " + e.getMessage());}
        finally {//            this.desconectar();
        }
        return res;
    }
}
