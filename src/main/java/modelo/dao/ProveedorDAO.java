package modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Proveedor;
import conexion.ConectaBD;

public class ProveedorDAO {
    private Connection cnx;
    
    public ProveedorDAO() {
        cnx= new ConectaBD().getConnection();
    }
    
    public List<Proveedor> getList(){
        List<Proveedor> lista = null;
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "select CodProveedor, Nombre, Contacto, Pais from Proveedor";
        try {
            ps = cnx.prepareStatement(cadSQL);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Proveedor p = new Proveedor(
                        rs.getInt("codProveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("pais"));
                
                lista.add(p);
            }
            rs.close();
        } catch (SQLException ex) {
        }
        return lista;
    }
}
