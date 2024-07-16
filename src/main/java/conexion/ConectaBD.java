package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaBD {
//esta es la conexion para la base de datos 

    public Connection getConnection() {
        Connection cnx = null;

        String url = "jdbc:mysql://localhost:3306/enchufate?useTimeZone=true&serverTimezone=UTC&autoReconnect=true";
        String user = "root";
        String clave = "";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
            cnx = DriverManager.getConnection(url, user, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
}
