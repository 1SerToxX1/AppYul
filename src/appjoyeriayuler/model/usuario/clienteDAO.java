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
}