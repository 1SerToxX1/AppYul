package appjoyeriayuler.moduloCliente;

import appjoyeriayuler.model.usuario.clienteDAO;
import appjoyeriayuler.securityModule.usuario.ControlSesionUsuario;
import appjoyeriayuler.shared.MensajeSistema;

public class controlRegistrarCliente {

    private FormRegistrarCliente formRegistrarCliente; // Referencia al formulario
    private clienteDAO clienteDAO;  // Instancia del clienteDAO para verificar si el DNI ya existe

    // Constructor para inicializar clienteDAO
    public controlRegistrarCliente() {
        clienteDAO = new clienteDAO(); // Inicializamos el clienteDAO
    }

    // Método para mostrar el formulario de Registrar Cliente
    public void formRegistrarClienteShow() {
        // Si la sesión está activa, crea y muestra el formulario
        if (ControlSesionUsuario.estaLogueado()) {
            formRegistrarCliente = new FormRegistrarCliente();  // Crear el formulario aquí
            formRegistrarCliente.setVisible(true);  // Mostrar el formulario
        } else {
            MensajeSistema.mostrarError("Debes iniciar sesión para acceder a esta opción.");
        }
    }

    // Validar si el DNI está vacío
    public boolean esDniValido(String dni) {
        if (dni == null || dni.isEmpty()) {
            return false;
        }
        return true;
    }

    // Validar si el DNI solo tiene números
    public boolean esNumerico(String dni) {
        if (!dni.matches("[0-9]+")) {
            MensajeSistema.mostrarError("El DNI debe tener datos numéricos.");
            return false;
        }
        return true;
    }

    // Validar si el DNI tiene exactamente 8 dígitos
    public boolean esLongitudValida(String dni) {
        if (dni.length() > 8) {
            MensajeSistema.mostrarError("El DNI solo debe contener 8 dígitos.");
            return false;
        }
        return true;
    }

    // Método para verificar si el DNI ya existe en la base de datos
    public boolean dniExiste(String dni) {
        if (clienteDAO.dniExiste(dni)) {
            MensajeSistema.mostrarError("El DNI ya está registrado.");
            return true;  // Si el DNI existe, retornamos true para indicar que ya está registrado
        }
        return false;  // Si el DNI no existe, retornamos false
    }
}
