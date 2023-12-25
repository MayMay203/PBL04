package com.example.pbl04.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.example.pbl04.entity.*;

@Service
public class ImageProcessorService {
    public String ImageProcess(MultipartFile imageFile, Integer id, String type) throws IOException {
//        ----------------ảnh----------------------
        String firstNameImage = "";
        if (imageFile != null && !imageFile.isEmpty()) {
            System.out.println("Input image is not not not null");
            // Nếu có file ảnh mới được chọn, thực hiện cập nhật đường dẫn ảnh mới
//            var parts = anhMon.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
//            var fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
            switch (type){
                case "tai_khoan":
                    firstNameImage = "TK";
                    break;
                case "hoat_dong":
                    firstNameImage = "HD";
                    break;
                default:
                    firstNameImage = "";
            }
            String fileName = firstNameImage + id + "nn.png";
            System.out.println("Ten file anh Controller:" + fileName);
            // Đường dẫn đầy đủ cho file mới
            // Đường dẫn thư mục images trong resources/static
            Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
            System.out.println("uploadPath:" + uploadPath);
            // Tạo đường dẫn đầy đủ cho file mới
            Path newImagePath = uploadPath.resolve(fileName);
            System.out.println("newImagePath:" + newImagePath);
            // Kiểm tra xem tên file đã tồn tại hay chưa
            if (Files.exists(newImagePath)) {
                Files.delete(newImagePath);
            }
            // Lưu ảnh vào thư mục images
            Files.copy(imageFile.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
            // Cập nhật thông tin ảnh trong CSDL nếu cần
            System.out.println("Ten ...Controller:" + "/images/"+fileName);
//        System.out.println("Ten file anh Controller:" + fileName);
//            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());

            // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
            String urlImageInDB = "/images/" + fileName;
//            foodSaved.setAnhMon("/images/" + fileName);
            System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
            return urlImageInDB;
        }
        System.out.println("Input image is nulllllll");
        return null;
    }
}
