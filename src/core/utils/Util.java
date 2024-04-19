package core.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Util {

    // Mostrar mensaje de alerta
    public void alertMessage() {
        JOptionPane.showMessageDialog(null, "Ha ocurrido un problema", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }

    public void alertMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }
    
     public void alertMessage(String message, String titleMessage) {
        JOptionPane.showMessageDialog(null, message, titleMessage, JOptionPane.ERROR_MESSAGE);
    }

    public void alertMessage(String message, String titleMessage, Component component) {
        JOptionPane.showMessageDialog(component, message, titleMessage, JOptionPane.ERROR_MESSAGE);
    }

    public void alertMessage(String message, String titleMessage, Component component, int jop) {
        JOptionPane.showMessageDialog(component, message, titleMessage, jop);
    }

    // Centrar vista en la pantalla
    public void centerOnScreen(final Component viewComponent, final boolean absolute) {
        final int width = viewComponent.getWidth();
        final int height = viewComponent.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        if (!absolute) {
            x /= 2;
            y /= 2;
        }
        viewComponent.setLocation(x, y);
    }
}
