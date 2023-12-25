package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
public class PersonalityController {
    private final SessionService sessionService;
    private final ActivityService activityService;
    private final MemberService memberService;
    private final ImageProcessorService imageProcessorService;
    private final AccountService accountService;

    private final TopicService topicService;
    private final SummaryService summaryService;
    private final RegisterService registerService;

    @Autowired
    public PersonalityController(SessionService sessionService, ActivityService activityService,
                                 MemberService memberService, ImageProcessorService imageProcessorService, AccountService accountService, TopicService topicService, SummaryService summaryService, RegisterService registerService) {
        this.sessionService = sessionService;
        this.activityService = activityService;
        this.memberService = memberService;
        this.imageProcessorService = imageProcessorService;
        this.accountService = accountService;
        this.topicService = topicService;
        this.summaryService = summaryService;
        this.registerService = registerService;
    }

    @GetMapping("/trang-ca-nhan")
    public String show(Model model,
                       @RequestParam("id") Integer id,
                       @ModelAttribute("message") String message,
                       HttpSession session) {
        List<Hoatdong> actListIsHost = activityService.getAllActivityIsHost(id);
        model.addAttribute("actListIsHost", actListIsHost);
        List<Dangky> listRegisIsHostActi = new ArrayList<>();
        List<Integer> listSummaryIsHostActi = new ArrayList<>();
        for(Hoatdong hd : actListIsHost){
            //lấy đăng ký:
            listRegisIsHostActi.add(activityService.getRegisterInfo(hd.getId()));
            //lấy tổng kết
           if(summaryService.getSummaryByID(hd.getId()) != null){
               listSummaryIsHostActi.add(summaryService.getSummaryByID(hd.getId()).getId());
               System.out.println("Tổng kết host:"+ summaryService.getSummaryByID(hd.getId()).getId() +"---"+summaryService.getSummaryByID(hd.getId()).getMaHD().getTenhd());
           }
           else{
               listSummaryIsHostActi.add(-1);
           }
        }
        model.addAttribute("listRegisIsHostActi",listRegisIsHostActi);
        model.addAttribute("listSummaryIsHostActi", listSummaryIsHostActi);

        List<Hoatdong> actListIsMember = activityService.getAllActivityIsMember(id);
        model.addAttribute("actListIsMember", actListIsMember);
        List<Dangky> listRegisIsMemberActi = new ArrayList<>();
        List<Integer> listSummaryIsMemberActi = new ArrayList<>();
        for(Hoatdong hd : actListIsMember){
            //lấy đăng ký:
            listRegisIsMemberActi.add(registerService.getRegisterIsMember(hd.getId(), id));
            //lấy tổng kết
            if(summaryService.getSummaryByID(hd.getId()) != null){
                listSummaryIsMemberActi.add(summaryService.getSummaryByID(hd.getId()).getId());
                System.out.println("Tổng kết member:"+ summaryService.getSummaryByID(hd.getId()).getId() +"---"+summaryService.getSummaryByID(hd.getId()).getMaHD().getTenhd());
            }
            else{
                listSummaryIsMemberActi.add(-1);
            }
        }
        model.addAttribute("listRegisIsMemberActi", listRegisIsMemberActi);
        model.addAttribute("listSummaryIsMemberActi", listSummaryIsMemberActi);

        model.addAttribute("activity", new Hoatdong());
//        Dangky getRegisterInfo = activityService.getRegisterInfo(id)
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("listTopics", listTopics);

        sessionService.createSessionModel(model, session);
        // Truyền thông điệp từ RedirectAttributes đến model
        model.addAttribute("message", message);
        return "TrangCaNhan";
    }

//    @PostMapping("/sua-thong-tin-ca-nhan")
//    public String insertTable(@ModelAttribute(value="user") Thanhvien thanhvien,
//                                           @RequestParam(name = "id", required = false) Integer id,
//                                           @RequestParam(value="imageInput", required = false) MultipartFile imageInput,
////                              @RequestParam(value="imageInput", required = false) String anh,
//                                           RedirectAttributes redirectAttributes,
//                                           HttpSession session, Model model) throws IOException, InterruptedException {
//
//            System.out.println(thanhvien.getMaTK()+thanhvien.getHoTen()+thanhvien.getSoDienThoai()+thanhvien.getDiaChi()+thanhvien.getEmail()+  thanhvien.getDiaChi() + imageInput);
//            System.out.println(thanhvien.getNgaySinh());
////        Map<String, Object> response = new HashMap<>();
////        memberService.updateInfor(thanhvien);
//
////        =========================================
//
//        if (imageInput != null && !imageInput.isEmpty()) {
//            System.out.println("Input image is not null");
//
//            // Lưu ảnh vào thư mục images
////            CompletableFuture.runAsync(() -> {
//                String urlImage = null;
//            String fileName = "TK" + id + ".png";
//
////        System.out.println("Ten file anh Controller:" + fileName);
////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
//
//                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
////                String urlImageInDB = imageProcessorService.ImageProcess(imageInput, thanhvien.getMaTK().getId(), "tai_khoan" );
////            foodSaved.setAnhMon("/images/" + fileName);
//                Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
//                System.out.println("uploadPath:" + uploadPath);
//                // Tạo đường dẫn đầy đủ cho file mới
//                Path newImagePath = uploadPath.resolve(fileName);
//                System.out.println("newImagePath:" + newImagePath);
//                // Kiểm tra xem tên file đã tồn tại hay chưa
//                if (Files.exists(newImagePath)) {
//                    Files.delete(newImagePath);
//                }
//                // Lưu ảnh vào thư mục images
//                Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
//                // Cập nhật thông tin ảnh trong CSDL nếu cần
//                System.out.println("Ten ...Controller:" + "/images/"+fileName);
////        System.out.println("Ten file anh Controller:" + fileName);
////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
//
//                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
//                String urlImageInDB = "/images/" + fileName;
////            foodSaved.setAnhMon("/images/" + fileName);
//                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
//                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
////            return urlImageInDB;
//                thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
//            memberService.updateInfor(thanhvien);
////                try {
//                    //urlImage = imageProcessorService.ImageProcess(imageInput, thanhvien.getMaTK().getId(), "tai_khoan");
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
//
//
//
//        } else {
//            System.out.println("Input image is null");
//            // Xử lý trường hợp người dùng không chọn ảnh
//        }
//
//
////        ===============================
////        if (imageInput != null && !imageInput.isEmpty()) {
////            System.out.println("Input image is not not not null");
////            // Lấy tên tệp gốc
////            String anh = StringUtils.cleanPath(imageInput.getOriginalFilename());
////
////            // Nếu có file ảnh mới được chọn, thực hiện cập nhật đường dẫn ảnh mới
//////            var parts = anhMon.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
//////            var fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
//////            switch (type){
//////                case "tai_khoan":
//////                    firstNameImage = "TK";
//////                    break;
//////                case "hoat_dong":
//////                    firstNameImage = "HD";
//////                    break;
//////                default:
//////                    firstNameImage = "";
//////            }
//////            String fileName = "TK" + id + "nnnn.png";
//////            var parts = anh.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
//////            var fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
////            System.out.println("Ten file anh Controller:" + fileName);
////            // Đường dẫn đầy đủ cho file mới
////            // Đường dẫn thư mục images trong resources/static
////            Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
////            System.out.println("uploadPath:" + uploadPath);
////            // Tạo đường dẫn đầy đủ cho file mới
////            Path newImagePath = uploadPath.resolve(fileName);
////            System.out.println("newImagePath:" + newImagePath);
////            // Kiểm tra xem tên file đã tồn tại hay chưa
////            if (!Files.exists(newImagePath)) {
//////                Files.delete(newImagePath);
////                Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
////            }
////            // Lưu ảnh vào thư mục images
////
////            // Cập nhật thông tin ảnh trong CSDL nếu cần
////            System.out.println("Ten ...Controller:" + "/images/"+fileName);
//////        System.out.println("Ten file anh Controller:" + fileName);
//////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
////
////            // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
////            String urlImageInDB = "/images/" + fileName;
//////            foodSaved.setAnhMon("/images/" + fileName);
////            System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
//////            return urlImageInDB;
////            thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
////        }
//////        accountService.updateAvatar();
////        memberService.updateInfor(thanhvien);
////        ----------------------------------------------------------
//        // Thêm thông điệp vào RedirectAttributes
////        memberService
//
//        redirectAttributes.addFlashAttribute("message", "Thông báo: Thông tin đã được cập nhật thành công!");
//        System.out.println("ĐI đén trang cá nhân lại");
////        Thread.sleep(10000);
//        sessionService.createSessionModel(model, session);
//
//        return "redirect:/trang-ca-nhan?id=" + thanhvien.getMaTK().getId();
//
//    }
//}

    @PostMapping("/sua-thong-tin-ca-nhan")
    public ResponseEntity<Map<String, Object>> insertTable(@ModelAttribute(value = "user") Thanhvien thanhvien,
                                                           @RequestParam(name = "id", required = false) Integer id,
                                                           @RequestParam(value = "imageInput", required = false) MultipartFile imageInput,
                                                           RedirectAttributes redirectAttributes,
                                                           HttpSession session, Model model) throws IOException, InterruptedException {

        Map<String, Object> response = new HashMap<>();
        String fileName;
        try {
            if (imageInput != null && !imageInput.isEmpty()) {
                System.out.println("Input image is not not not null");
                // Lấy tên tệp gốc
                String anh = StringUtils.cleanPath(imageInput.getOriginalFilename());
                var parts = anh.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
                fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
                // Giới hạn chiều dài của fileName thành 50 ký tự nếu nó dài hơn
                int maxLength = 50;
                if (fileName.length() > maxLength) {
                    fileName = fileName.substring(0, maxLength);
                }
                System.out.println("Ten file anh Controller:" + fileName);
                // Đường dẫn đầy đủ cho file mới
                // Đường dẫn thư mục images trong resources/static
                Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
                System.out.println("uploadPath:" + uploadPath);
                // Tạo đường dẫn đầy đủ cho file mới
                Path newImagePath = uploadPath.resolve(fileName);
                System.out.println("newImagePath:" + newImagePath);
                String urlImageInDB;
                // Kiểm tra xem tên file đã tồn tại hay chưa
                if (!Files.exists(newImagePath)) {
                    Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
//                    urlImageInDB = "/images/" + fileName;
//                    thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
                } else {
                    // Nếu file đã tồn tại, kiểm tra xem nội dung có khác nhau hay không
                    byte[] newImageContent = imageInput.getBytes();
                    byte[] existingImageContent = Files.readAllBytes(newImagePath);

                    if (!Arrays.equals(newImageContent, existingImageContent)) {
                        fileName = "new" + fileName ;
                        newImagePath = uploadPath.resolve(fileName);
                        // Nếu nội dung khác nhau, lưu file mới và cập nhật đường dẫn ảnh
                        Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
//                        urlImageInDB = "/images/" + "new" + fileName;
//                        thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
                    } else {
//                        urlImageInDB = "/images/" + fileName;
                        // Nếu nội dung giống nhau, không cần thực hiện thêm hành động gì
                        System.out.println("Nội dung giống nhau, không cần lưu.");
                    }
                }
                // Cập nhật thông tin ảnh trong CSDL nếu cần
                System.out.println("Ten ...Controller:" + "/images/" + fileName);
//        System.out.println("Ten file anh Controller:" + fileName);
//            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());

                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
//                String urlImageInDB = "/images/" + fileName;
//            foodSaved.setAnhMon("/images/" + fileName);
                urlImageInDB = "/images/" + fileName;
                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
//            return urlImageInDB;
                thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
            }
//        accountService.updateAvatar();
            memberService.updateInfor(thanhvien);
//        ----------------------------------------------------------
            response.put("message", "Thông báo: Thông tin đã được cập nhật thành công!");
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            response.put("message", "Có lỗi xảy ra khi cập nhật thông tin thành viên.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



//    @PostMapping("/sua-thong-tin-ca-nhan")
//    public ResponseEntity<Map<String, Object>> insertTable(@ModelAttribute(value = "user") Thanhvien thanhvien,
//                                                           @RequestParam(name = "id", required = false) Integer id,
//                                                           @RequestParam(value = "imageInput", required = false) MultipartFile imageInput,
//                                                           RedirectAttributes redirectAttributes,
//                                                           HttpSession session, Model model) throws IOException, InterruptedException {
//
//        Map<String, Object> response = new HashMap<>();
//        try {
//            // ... (your existing code)
////===============================
//            if (imageInput != null && !imageInput.isEmpty()) {
//                System.out.println("Input image is not not not null");
//                // Lấy tên tệp gốc
//                String anh = StringUtils.cleanPath(imageInput.getOriginalFilename());
//
//                // Nếu có file ảnh mới được chọn, thực hiện cập nhật đường dẫn ảnh mới
////            var parts = anhMon.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
////            var fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
////            switch (type){
////                case "tai_khoan":
////                    firstNameImage = "TK";
////                    break;
////                case "hoat_dong":
////                    firstNameImage = "HD";
////                    break;
////                default:
////                    firstNameImage = "";
////            }
//            String fileName = "TK" + id + "nnnn.png";
//
////        System.out.println("Ten file anh Controller:" + fileName);
////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
//
//                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
////                String urlImageInDB = imageProcessorService.ImageProcess(imageInput, thanhvien.getMaTK().getId(), "tai_khoan" );
////            foodSaved.setAnhMon("/images/" + fileName);
//                Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
//                System.out.println("uploadPath:" + uploadPath);
//                // Tạo đường dẫn đầy đủ cho file mới
//                Path newImagePath = uploadPath.resolve(fileName);
//                System.out.println("newImagePath:" + newImagePath);
//                // Kiểm tra xem tên file đã tồn tại hay chưa
//                if (Files.exists(newImagePath)) {
//                    Files.delete(newImagePath);
//                }
//                // Lưu ảnh vào thư mục images
//                Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
//                // Cập nhật thông tin ảnh trong CSDL nếu cần
//                System.out.println("Ten ...Controller:" + "/images/"+fileName);
////        System.out.println("Ten file anh Controller:" + fileName);
////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
//
//                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
//                String urlImageInDB = "/images/" + fileName;
////            foodSaved.setAnhMon("/images/" + fileName);
//                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
//                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
////            return urlImageInDB;
//                thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
//            }
////        accountService.updateAvatar();
//            memberService.updateInfor(thanhvien);
////        ----------------------------------------------------------
//            response.put("message", "Thông báo: Thông tin đã được cập nhật thành công!");
//            response.put("success", true);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            // Xử lý lỗi nếu có
//            response.put("message", "Có lỗi xảy ra khi cập nhật thông tin thành viên.");
//            response.put("success", false);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
}
