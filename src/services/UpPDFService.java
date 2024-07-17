package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class UpPDFService {

    private static final String UPLOAD_DIR = "/path/to/upload/directory/";

    public String uploadFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("El archivo no existe");
        }

        
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

       
        String fileName = file.getName();
        String filePath = UPLOAD_DIR + fileName;
        while (Files.exists(Paths.get(filePath))) {
            String uniqueID = UUID.randomUUID().toString();
            fileName = uniqueID + "_" + file.getName();
            filePath = UPLOAD_DIR + fileName;
        }

        
        Files.copy(file.toPath(), Paths.get(filePath));

        return filePath;
    }
}
