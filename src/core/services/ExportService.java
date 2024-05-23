package core.services;

import it.firegloves.mempoi.MemPOI;
import it.firegloves.mempoi.builder.MempoiBuilder;
import it.firegloves.mempoi.domain.MempoiSheet;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExportService {

    public static void exportToExcel(PreparedStatement stmt, String filename) throws IOException, InterruptedException, ExecutionException {
        String fileExtension = ".xlsx";
        prepareFile(stmt, filename, fileExtension);
    }

    private static void prepareFile(PreparedStatement stmt, String filename, String fileExtension) throws IOException, InterruptedException, ExecutionException {
        String basePath = "D:\\Apps\\src\\src-utp\\proyecto-final-algoritmos\\src\\exports\\";
        File fileDownload = new File(basePath + filename + fileExtension);
        exportFile(stmt, fileDownload);
    }

    private static void exportFile(PreparedStatement stmt, File fileDownload) throws IOException, InterruptedException, ExecutionException {
        /*
            Docs: https://firegloves.github.io/MemPOI/
        */
        
        MemPOI memPOI = MempoiBuilder.aMemPOI()
                .withFile(fileDownload)
                .addMempoiSheet(new MempoiSheet(stmt))
                .build();

        CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
        String absoluteFileName = fut.get();

        System.out.println("El archivo se ha descargado en: " + absoluteFileName);
    }
}
