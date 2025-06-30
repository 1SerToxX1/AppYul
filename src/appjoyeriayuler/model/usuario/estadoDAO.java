package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import appjoyeriayuler.shared.MensajeSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class estadoDAO extends Conexion {

    public boolean UsuarioActivo(String username) {
        boolean activo = false;

        String sql = """
            SELECT e.estado
            FROM usuario u
            INNER JOIN estado e ON u.idEstado = e.idEstado
            WHERE u.usuario = ?
        """;

        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                activo = "activo".equalsIgnoreCase(estado);
            }

        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error al verificar el estado del usuario");
            e.printStackTrace();
        }

        return activo;
    }
}
