package core.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private String uploadDir = "uploads"; // Directorio donde se guardarán los archivos
    Path path;

    public FileService() {
        // Crear el directorio de subida si no existe
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para subir un archivo
    public String uploadFile(File selectedFile) throws IOException {
        try {
            File targetFile = new File(uploadDir + File.separator + selectedFile.getName());

            if (!targetFile.exists()) {
                throw new IOException("El archivo no existe");
            }

            InputStream fileContent = new FileInputStream(selectedFile);
            FileOutputStream outputStream = new FileOutputStream(targetFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return targetFile.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Método para descargar un archivo
    public byte[] downloadFile(String fileName, String destinationPath) throws IOException {

        try {
            File sourceFile = new File(uploadDir + File.separator + fileName);

            if (!sourceFile.exists()) {
                throw new IOException("El archivo no existe");
            }

            File destinationFile = new File(destinationPath);
            FileInputStream inputStream = new FileInputStream(sourceFile);
            FileOutputStream outputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }
}
