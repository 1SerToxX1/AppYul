package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPrivilegioDAO extends Conexion {

    public List<String> obtenerPrivilegios(int idUsuario) throws SQLException {
        List<String> privilegios = new ArrayList<>();

        String sql = """
            SELECT p.label AS privilegio
            FROM privilegiosUsuario pu
            INNER JOIN privilegio p ON pu.idPrivilegio = p.idPrivilegio
            WHERE pu.idUsuario = ?
        """;

        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                privilegios.add(rs.getString("privilegio"));
            }
        }

        return privilegios;
    }
}
