package core.utils;

import java.time.LocalDate;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Validation {

    private final JComponent componentToValidate;
    private final List<Predicate<JComponent>> validationRules;
    private final List<String> errorMessages;

    // Constructor privado para asegurar que se utilice el builder pattern
    private Validation(JComponent component) {
        this.componentToValidate = component;
        this.validationRules = new ArrayList<>();
        this.errorMessages = new ArrayList<>();
    }

    // Método estático para iniciar la validación con un componente específico
    public static Validation validateComponent(JComponent component) {
        return new Validation(component);
    }

    // Función de validación requerida para JTextField
    public Validation required() {
        validationRules.add(comp -> {
            if (comp instanceof JTextField) {
                String text = ((JTextField) comp).getText().trim();
                return !text.isEmpty();
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add("Este campo es requerido.");
        return this;
    }

    // Función de validación requerida para JComboBox con condición personalizada
    public Validation required(Predicate<JComboBox<?>> condition) {
        validationRules.add(comp -> {
            if (comp instanceof JComboBox) {
                return condition.test((JComboBox<?>) comp);
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add("Debe seleccionar una opción válida.");
        return this;
    }

    // Función de validación de longitud mínima
    public Validation min(int minLength) {
        validationRules.add(comp -> {
            if (comp instanceof JTextField jTextField) {
                String text = jTextField.getText().trim();
                return text.length() >= minLength;
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add(String.format("El campo debe tener al menos %d caracteres.", minLength));
        return this;
    }

    // Función de validación de longitud máxima
    public Validation max(int maxLength) {
        validationRules.add(comp -> {
            if (comp instanceof JTextField jTextField) {
                String text = jTextField.getText().trim();
                return text.length() <= maxLength;
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add(String.format("El campo no debe exceder los %d caracteres.", maxLength));
        return this;
    }
    
    // Nueva función de validación para verificar correos electrónicos
    public Validation email() {
        validationRules.add(comp -> {
            if (comp instanceof JTextField jTextField) {
                String text = jTextField.getText().trim();
                return text.contains("@");
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add("El campo debe contener una dirección de correo electrónico válida.");
        return this;
    }
     // Nueva función de validación para verificar la edad mínima
    public Validation validateAge() {
        validationRules.add(comp -> {
            if (comp instanceof JTextField jTextField) {
                String text = jTextField.getText().trim();
                if (text.length() >= 4) {
                    try {
                        int birthYear = Integer.parseInt(text.substring(text.length() - 4));
                        int currentYear = LocalDate.now().getYear();
                        int age = currentYear - birthYear;
                        return age >= 18;
                    } catch (NumberFormatException e) {
                        return false; // Los últimos 4 caracteres no son un año válido
                    }
                }
                return false; // La cadena no tiene al menos 4 caracteres
            }
            return true; // No aplica para otros tipos de componentes
        });
        errorMessages.add("Debe tener al menos 18 años.");
        return this;
    }
    

    // Método para ejecutar la validación y mostrar el mensaje de error si falla
    public boolean validate() {
        boolean isValid = true;
        StringBuilder errorMessageBuilder = new StringBuilder();

        for (int i = 0; i < validationRules.size(); i++) {
            Predicate<JComponent> rule = validationRules.get(i);
            if (!rule.test(componentToValidate)) {
                isValid = false;
                errorMessageBuilder.append(errorMessages.get(i)).append("\n");
            }
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(componentToValidate, errorMessageBuilder.toString().trim(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
    }

}
