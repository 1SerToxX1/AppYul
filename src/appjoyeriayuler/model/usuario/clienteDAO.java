/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlo
 */
public class clienteDAO extends Conexion {

    // Método para verificar si el DNI ya existe en la base de datos
    public boolean dniExiste(String dni) {
        String sql = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
        try (Connection conn = conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni); // Establecer el valor del DNI en la consulta
            ResultSet rs = stmt.executeQuery(); // Ejecutar la consulta

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si existe el DNI, devolver true
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo del error
        }
        return false; // Si no existe el DNI o si hay un error
    }
    
    // Método para verificar si el RUC ya existe en la base de datos
public boolean rucExiste(String ruc) {
    String sql = "SELECT COUNT(*) FROM clientes WHERE ruc = ?";
    
    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, ruc); // Establecer el valor del RUC en la consulta
        ResultSet rs = stmt.executeQuery(); // Ejecutar la consulta

        if (rs.next()) {
            return rs.getInt(1) > 0; // Si existe el RUC, devolver true
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Manejo del error
    }

    return false; // Si no existe el RUC o si hay un error
}

public boolean telefonoExiste(String telefono) {
    String sql = "SELECT COUNT(*) FROM clientes WHERE telefono = ?";
    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, telefono);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean registrarCliente(String dni, String nombre, String ruc, String telefono) {
    String sql = "INSERT INTO clientes (dni, cliente, ruc, telefono) VALUES (?, ?, ?, ?)";

    try (Connection conn = conectarBD();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, dni);
        stmt.setString(2, nombre);

        // Guardar null si RUC está vacío
        if (ruc == null || ruc.trim().isEmpty()) {
            stmt.setNull(3, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(3, ruc);
        }

        // Guardar null si Teléfono está vacío
        if (telefono == null || telefono.trim().isEmpty()) {
            stmt.setNull(4, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(4, telefono);
        }

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}


    
}