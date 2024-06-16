package modelo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.Categoria;
import conexion.ConectaBD;

public class CategoriaDAO {
    private Connection cnx;
    
    public CategoriaDAO() {
        cnx= new ConectaBD().getConnection();
    }
    
    public List<Categoria> getList(){
        List<Categoria> lista = null;
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "select CodCategoria, Nombre from Categoria";
        try {
            ps = cnx.prepareStatement(cadSQL);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("codCategoria"),
                        rs.getString("nombre"));
                lista.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
        }
        return lista;
    }
}
