package core.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Util {

    // ANSI escape codes for bold text
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_RESET = "\033[0m";

    // Mostrar mensaje de alerta
    public void alertMessage(String message) {
        this.alertMessage(message, false);
    }

    public void alertMessage(String message, boolean isError) {
        if (isError) {
            JOptionPane.showMessageDialog(null, message, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
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

    // Imprimir tabla en terminal
    public void imprimirTabla(String[] encabezados, Object[][] data) {
        int[] columnWidths = new int[encabezados.length];

        // Calcula el ancho máximo de cada columna incluyendo los encabezados
        for (int i = 0; i < encabezados.length; i++) {
            columnWidths[i] = encabezados[i].length();
            for (Object[] row : data) {
                if (row[i] != null) {
                    columnWidths[i] = Math.max(columnWidths[i], row[i].toString().length());
                }
            }
        }

        // Imprime la tabla con bordes
        imprimirSeparador(columnWidths);
        imprimirFila(encabezados, columnWidths, true); // Encabezados en mayúsculas y negrita
        imprimirSeparador(columnWidths);
        for (Object[] row : data) {
            imprimirFila(row, columnWidths, false);
            imprimirSeparador(columnWidths);
        }

        System.out.print("\n");
    }

    private void imprimirSeparador(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            for (int i = 0; i < width + 2; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private void imprimirFila(Object[] row, int[] columnWidths, boolean isHeader) {
        for (int i = 0; i < row.length; i++) {
            System.out.print("| ");
            String cellContent = (row[i] != null) ? row[i].toString() : ""; // Manejar valores nulos
            if (isHeader) {
                cellContent = cellContent.toUpperCase(); // Convertir a mayúsculas
                System.out.print(ANSI_BOLD + cellContent + ANSI_RESET);
            } else {
                System.out.print(cellContent);
            }
            for (int j = cellContent.length(); j < columnWidths[i]; j++) {
                System.out.print(" ");
            }
            System.out.print(" ");
        }
        System.out.println("|");
    }

    // Función genérica para validar cualquier componente con reglas de validación
    public static boolean validateComponent(JComponent component, Predicate<JComponent> validationRule) {
        return validationRule.test(component);
    }

    /**
     * Calcula la edad de una persona basada en su fecha de nacimiento.
     *
     * @param fechaNacimiento La fecha de nacimiento en formato "dd/MM/yyyy".
     * @return La edad calculada como String.
     */
    public static String calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate currentDate = LocalDate.now();
        int edad = (int) ChronoUnit.YEARS.between(birthDate, currentDate);
        return String.valueOf(edad);
    }
}
