package appjoyeriayuler.shared;

import javax.swing.JOptionPane;

public class MensajeSistema {
    public static void mostrarAdvertencia(String mensaje, String sugerencia) {
        JOptionPane.showMessageDialog(null, mensaje + "\n" + sugerencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean mostrarConfirmacion(String mensaje, String titulo) {
    int opcion = JOptionPane.showConfirmDialog(
        null,
        mensaje,
        titulo,
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    return opcion == JOptionPane.YES_OPTION;
}

}
