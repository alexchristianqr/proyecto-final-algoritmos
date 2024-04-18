package core.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Util {

    // Mostrar mensaje de alerta
    public void alertMessage() {
        JOptionPane.showMessageDialog(null, "Invalid password. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    // Centrar vista en la pantalla
    public void centerOnScreen(final Component c, final boolean absolute) {
        final int width = c.getWidth();
        final int height = c.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        if (!absolute) {
            x /= 2;
            y /= 2;
        }
        c.setLocation(x, y);
    }
}
