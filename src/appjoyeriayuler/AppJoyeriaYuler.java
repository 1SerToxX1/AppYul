package appjoyeriayuler;

import appjoyeriayuler.moduloCliente.FormRegistrarCliente;
import appjoyeriayuler.moduloCliente.controlRegistrarCliente;
import appjoyeriayuler.securityModule.usuario.FormAutenticarUsuario;

public class AppJoyeriaYuler {
    public static void main(String[] args) {
        
        new FormAutenticarUsuario().setVisible(true);
        /*controlRegistrarCliente controlRegistrarCliente = new controlRegistrarCliente();
        controlRegistrarCliente.formRegistrarClienteShow();*/
    }
}
