package com.example.pbl04.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import javax.imageio.ImageIO;
public class Anh {
    public String convertBlobToBase64(Blob blob) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return encodeImageToBase64(inputStream);
        } catch (SQLException | IOException e) {
            // Handle the exception, log it, or throw a RuntimeException
            e.printStackTrace();
            return ""; // or some default value
        }
    }

        public String getimgdata(byte[] bytedata)
        { return Base64.getMimeEncoder().encodeToString(bytedata); }
    private String encodeImageToBase64(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return Base64.getEncoder().encodeToString(imageInByte);
    }
}
