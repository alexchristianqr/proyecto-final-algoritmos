package core.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class UploadService {

    public void upload(InputStream inputStream, String destinationPath) throws IOException {
        OutputStream outputStream = null;

        try {
            File file = new File(destinationPath);
            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            System.out.println("File uploaded successfully to: " + destinationPath);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
