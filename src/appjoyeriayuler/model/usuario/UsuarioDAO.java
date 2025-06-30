package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Conexion {

    public boolean existeUsuario(String usuario) throws SQLException {
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean validarPassword(String usuario, String contraseña) throws SQLException {
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ? AND contraseña = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean usuarioActivo(String usuario) throws SQLException {
        String sql = """
            SELECT u.usuario 
            FROM usuarios u
            INNER JOIN estado e ON u.idEstado = e.idEstado
            WHERE u.usuario = ? AND e.estado = 'activo'
        """;
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public List<String> obtenerPrivilegios(int idUsuario) throws SQLException {
        List<String> privilegios = new ArrayList<>();
        String sql = """
            SELECT p.label AS privilegio
            FROM privilegiosUsuario pu
            JOIN privilegio p ON pu.idPrivilegio = p.idPrivilegio
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

    public int obtenerIdPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT idUsuarios FROM usuarios WHERE usuario = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idUsuarios");
            }
        }

        return -1;
    }
}
