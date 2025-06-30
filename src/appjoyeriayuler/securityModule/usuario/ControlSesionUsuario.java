package appjoyeriayuler.securityModule.usuario;

public class ControlSesionUsuario {

    private static boolean sesionActiva = false;
    private static int idUsuario = -1;
    private static String nombreUsuario = null;

    public static boolean iniciarSesion(int id, String usuario) {
        sesionActiva = true;
        idUsuario = id;
        nombreUsuario = usuario;
        return true;
    }

    public static void cerrarSesion() {
        sesionActiva = false;
        idUsuario = -1;
        nombreUsuario = null;
    }

    public static boolean estaLogueado() {
        return sesionActiva;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }
    
    
}
