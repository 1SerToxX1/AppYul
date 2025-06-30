package appjoyeriayuler.securityModule.usuario;

import appjoyeriayuler.model.usuario.UsuarioDAO;
import appjoyeriayuler.model.usuario.UsuarioPrivilegioDAO;
import appjoyeriayuler.shared.MensajeSistema;
import java.sql.SQLException;
import java.util.List;

public class ControlAutenticarUsuario {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioPrivilegioDAO privilegioDAO = new UsuarioPrivilegioDAO();

    public boolean autenticarUsuario(String username, String password) {
        boolean resultado = false;

        // 1. Validar campos vacíos
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            MensajeSistema.mostrarAdvertencia(
                "Rellenar datos de usuario y/o contraseña",
                "Complete los campos antes de continuar");
            return false;
        }

        try {
            // 2. Verificar existencia del usuario
            if (!usuarioDAO.existeUsuario(username)) {
                MensajeSistema.mostrarError("No se ha encontrado el usuario");
                return false;
            }

            // 3. Verificar contraseña
            if (!usuarioDAO.validarPassword(username, password)) {
                MensajeSistema.mostrarError("El password es incorrecto");
                return false;
            }

            // 4. Verificar estado activo
            if (!usuarioDAO.usuarioActivo(username)) {
                MensajeSistema.mostrarAdvertencia(
                    "El usuario está inactivo",
                    "Consultar con el administrador");
                return false;
            }

            // 5. Obtener ID del usuario
            int idUsuario = usuarioDAO.obtenerIdPorUsuario(username);
            if (idUsuario == -1) {
                MensajeSistema.mostrarError("Error al obtener ID del usuario");
                return false;
            }

            // 6. Obtener privilegios usando el ID
            List<String> privilegios = privilegioDAO.obtenerPrivilegios(idUsuario);
            if (privilegios == null || privilegios.isEmpty()) {
                MensajeSistema.mostrarError("El usuario no tiene privilegios asignados");
                return false;
            }

            // 7. Iniciar sesión con ID y nombre
            if (!ControlSesionUsuario.iniciarSesion(idUsuario, username)) {
                MensajeSistema.mostrarError("No se pudo iniciar sesión para el usuario");
                return false;
            }

            System.err.println("Sesión iniciada: " + ControlSesionUsuario.estaLogueado());
            System.err.println("Usuario logueado: " + ControlSesionUsuario.getNombreUsuario());

            // 8. Mostrar pantalla de bienvenida
            new FormMenuPrincipal(privilegios).setVisible(true);
            resultado = true;

        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error al validar credenciales. Intente más tarde.");
            e.printStackTrace(); // En producción, usar un logger
        }

        return resultado;
    }
}
