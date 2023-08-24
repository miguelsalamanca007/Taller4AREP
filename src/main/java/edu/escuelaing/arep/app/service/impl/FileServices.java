package edu.escuelaing.arep.app.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServices {

    public byte[] readFileAsBytes(String filePath) throws IOException {
        File file = new File(".\\src\\main\\java\\edu\\escuelaing\\arep\\app\\files\\"+filePath);
        byte[] fileBytes;

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileBytes = new byte[(int) file.length()];
            fileInputStream.read(fileBytes);
        }

        return fileBytes;
    }

    public String getExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return ""; // No se encontró una extensión válida
    }

    public String getContentType(String fileName) {
        String response = "";
        String extension = this.getExtension(fileName);
        if (extension.equals("jpg")) {
            response = "Content-Type: image/jpg";
        } else if (extension.equals("js")) {
            response = "Content-Type: application/javascript";
        } else if (extension.equals("html")) {
            response = "Content-Type: text/html";
        } else if (extension.equals("css")) {
            response = "Content-Type: text/css";
        }

        return response;
    }
}
