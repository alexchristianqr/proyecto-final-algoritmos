package controllers;

import core.services.ResponseService;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import services.UpPDFService;

public class UpPDFController {

    private final UpPDFService UpPDFService;

    public UpPDFController() {
        UpPDFService = new UpPDFService();
    }

    public ResponseService<String> uploadFile() {
        ResponseService<String> response = new ResponseService<>();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int uploadReturnValue = fileChooser.showOpenDialog(null);

        if (uploadReturnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                if (selectedFile.exists()) {
                    String filePath = UpPDFService.uploadFile(selectedFile);
                    response.setSuccess(true);
                    response.setResult(filePath);
                    response.setMessage("Archivo subido: " + filePath);
                } else {
                    response.setSuccess(false);
                    response.setMessage("Archivo seleccionado no existe.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.setSuccess(false);
                response.setMessage("Error de subida: " + e.getMessage());
            }
        } else {
            response.setSuccess(false);
            response.setMessage("ERROR AL CARGAR ARCHIVO.");
        }

        return response;
    }
}
