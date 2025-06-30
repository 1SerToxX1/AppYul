package appjoyeriayuler.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    protected Connection conectarBD() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bdjoyeriaprueba", "root", "Racer2001."
        );
    }

    protected void desconectarBD(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
