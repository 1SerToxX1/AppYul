package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO extends Conexion {

    public boolean dniExiste(String dni) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean rucExiste(String ruc) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE ruc = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ruc);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean telefonoExiste(String telefono) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE telefono = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefono);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public boolean registrarCliente(String dni, String nombre, String ruc, String telefono) throws SQLException {
        String sql = "INSERT INTO clientes (dni, cliente, ruc, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            stmt.setString(2, nombre);

            if (ruc == null || ruc.trim().isEmpty()) {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(3, ruc);
            }

            if (telefono == null || telefono.trim().isEmpty()) {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(4, telefono);
            }

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<String> buscarCliente(String dni) throws SQLException {
    String sql = "SELECT cliente, ruc, telefono FROM clientes WHERE dni = ?";
    List<String> resultado = new ArrayList<>();

    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, dni);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            resultado.add(rs.getString("cliente"));   // índice 0
            resultado.add(rs.getString("ruc"));       // índice 1
            resultado.add(rs.getString("telefono"));  // índice 2
        }
    }

    return resultado;
}
    
    public Integer obtenerIdClientePorDNI(String dni) throws SQLException {
    String sql = "SELECT idCliente FROM clientes WHERE dni = ?";
    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, dni);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("idCliente");
        }
    }

    return null; // No encontrado
}

    public boolean actualizarCliente(int idCliente, String nombre, String ruc, String telefono) throws SQLException {
    String sql = "UPDATE clientes SET cliente = ?, ruc = ?, telefono = ? WHERE idCliente = ?";
    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombre);

        if (ruc == null || ruc.trim().isEmpty()) {
            stmt.setNull(2, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(2, ruc);
        }

        if (telefono == null || telefono.trim().isEmpty()) {
            stmt.setNull(3, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(3, telefono);
        }

        stmt.setInt(4, idCliente);

        return stmt.executeUpdate() > 0;
    }
}


}
