/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appjoyeriayuler.moduloCliente.EditarCliente;

import appjoyeriayuler.model.usuario.clienteDAO;
import appjoyeriayuler.moduloCliente.RegistrarCliente.FormRegistrarCliente;
import appjoyeriayuler.securityModule.usuario.ControlSesionUsuario;
import appjoyeriayuler.shared.MensajeSistema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class controlEditarCliente {
    private FormEditarCliente formEditarCliente; // Referencia al formulario
    private clienteDAO clienteDAO;  // Instancia del clienteDAO para verificar si el DNI ya existe

    // Constructor para inicializar clienteDAO
    public controlEditarCliente() {
        clienteDAO = new clienteDAO(); // Inicializamos el clienteDAO
    }

    // Método para mostrar el formulario de Editar Cliente
    public void formEditarClienteShow() {
        // Si la sesión está activa, crea y muestra el formulario
        if (ControlSesionUsuario.estaLogueado()) {
            formEditarCliente = new FormEditarCliente();  // Crear el formulario aquí
            formEditarCliente.setVisible(true);  // Mostrar el formularioformEditarCliente
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
    try {
        if (clienteDAO.dniExiste(dni)) {
            MensajeSistema.mostrarError("El DNI ya está registrado.");
            return true;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al verificar el DNI. Intente más tarde.");
        e.printStackTrace(); // Reemplazar por logger en producción
    }
    return false;
}

public boolean dninoExiste(String dni) {
    try {
        if (!clienteDAO.dniExiste(dni)) {
            MensajeSistema.mostrarError("El DNI no está registrado.");
            return false;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al verificar el DNI. Intente más tarde.");
        e.printStackTrace(); // Reemplazar por logger en producción
    }
    return true;
}

public List<String> buscarClienteDatos(String dni) {
    List<String> resultado = new ArrayList<>();

    try {
        resultado = clienteDAO.buscarCliente(dni);

        if (resultado.isEmpty()) {
            MensajeSistema.mostrarError("El cliente no está registrado.");
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al buscar el cliente.");
        e.printStackTrace();
    }

    return resultado;
}


        // Validar si el nombre del cliente está vacío
    public boolean esNombreClienteValido(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    // Validar si el nombre del cliente tiene más de 5 caracteres
    public boolean esNombreMinimo(String nombre) {
        if (nombre.length() <= 5) {
            MensajeSistema.mostrarError("El cliente tiene que contener más de 5 caracteres.");
            return false;
        }
        return true;
    }

    // Validar si el nombre del cliente contiene solo letras
    public boolean contieneSoloLetras(String nombre) {
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            MensajeSistema.mostrarError("El cliente tiene que contener solamente letras.");
            return false;
        }
        return true;
    }

    // Validar si el nombre del cliente no excede los 50 caracteres
    public boolean esNombreMaximo(String nombre) {
        if (nombre.length() > 50) {
            MensajeSistema.mostrarError("El cliente no puede tener más de 50 caracteres.");
            return false;
        }
        return true;
    }
    
    public boolean esRucVacio(String ruc) {
    if (ruc == null || ruc.trim().isEmpty()) {
        return true; // Está vacío
    }
    return false; // Tiene contenido
}
    
    // Validar si el RUC tiene solo números
public boolean esRucNumerico(String ruc) {
    if (!ruc.matches("[0-9]+")) {
        MensajeSistema.mostrarError("El RUC debe tener datos numéricos.");
        return false;
    }
    return true;
}

// Validar si el RUC tiene exactamente 11 dígitos
public boolean esRucLongitudValida(String ruc) {
    if (ruc.length() != 11) {
        MensajeSistema.mostrarError("El RUC solo debe contener 11 dígitos.");
        return false;
    }
    return true;
}

// Verificar si el RUC ya está registrado en la base de datos
public boolean rucExiste(String ruc) {
    try {
        if (clienteDAO.rucExiste(ruc)) {
            MensajeSistema.mostrarError("El RUC se encuentra registrado.");
            return true;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al verificar el RUC. Intente más tarde.");
        e.printStackTrace();
    }
    return false;
}

// Validar si el teléfono está vacío
public boolean esTelefonoVacio(String telefono) {
    return telefono == null || telefono.trim().isEmpty();
}

// Validar si el teléfono solo tiene números
public boolean esTelefonoNumerico(String telefono) {
    if (!telefono.matches("[0-9]+")) {
        MensajeSistema.mostrarError("El Teléfono debe tener datos numéricos.");
        return false;
    }
    return true;
}

// Validar si el teléfono tiene exactamente 9 dígitos
public boolean esTelefonoLongitudValida(String telefono) {
    if (telefono.length() != 9) {
        MensajeSistema.mostrarError("El Teléfono solo debe contener 9 dígitos.");
        return false;
    }
    return true;
}

// Validar si el teléfono ya existe en la base de datos
public boolean telefonoExiste(String telefono) {
    try {
        if (clienteDAO.telefonoExiste(telefono)) {
            MensajeSistema.mostrarError("El Teléfono se encuentra registrado.");
            return true;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al verificar el Teléfono. Intente más tarde.");
        e.printStackTrace();
    }
    return false;
}

// Método para registrar un cliente desde el controller con mensaje incluido
public boolean registrarCliente(String dni, String nombre, String ruc, String telefono) {
    try {
        boolean registrado = clienteDAO.registrarCliente(dni, nombre, ruc, telefono);

        if (registrado) {
            MensajeSistema.mostrarInfo("Cliente registrado correctamente.");
            return true;
        } else {
            MensajeSistema.mostrarError("Ocurrió un error al registrar el cliente.");
            return false;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al registrar el cliente. Intente más tarde.");
        e.printStackTrace();
        return false;
    }
}

public boolean actualizarCliente(String dni, String nombre, String ruc, String telefono) {
    // Confirmación antes de editar
    if (!MensajeSistema.mostrarConfirmacion("¿Está seguro de editar este cliente?", "Mensaje")) {
        return false;
    }

    try {
        // Obtener idCliente por el DNI
        Integer idCliente = clienteDAO.obtenerIdClientePorDNI(dni);
        if (idCliente == null) {
            MensajeSistema.mostrarError("No se encontró el cliente con el DNI proporcionado.");
            return false;
        }

        // Intentar actualizar
        boolean actualizado = clienteDAO.actualizarCliente(idCliente, nombre, ruc, telefono);

        if (actualizado) {
            MensajeSistema.mostrarInfo("Cliente actualizado correctamente.");
            return true;
        } else {
            MensajeSistema.mostrarError("No se pudo actualizar el cliente.");
            return false;
        }
    } catch (Exception e) {
        MensajeSistema.mostrarError("Error al actualizar el cliente.");
        e.printStackTrace();
        return false;
    }
}



}

