package core.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class LocalDBService {

    private final String path = "data.bin";

    public Object cargarDB() {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (ois != null) {
                return ois;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo [data.bin] - Mensaje: " + ex);
        }
        return null;
    }

    public void guardarDB(Object data) {
        System.out.println("data es: " + data);
        try {
            FileOutputStream fos = new FileOutputStream(path);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(data);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al grabar en archivo [data.bin] - Mensaje: " + ex);
        }
    }
}
