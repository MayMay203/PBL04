package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.RegisterService;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.TopicService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Controller
public class ActivityController {
    private final ActivityService activityService;
    private final SessionService sessionService;
    private final TopicService topicService;
    private final RegisterService registerService;

    @Autowired
    public ActivityController(ActivityService activityService, TopicService topicService, SessionService sessionService, RegisterService registerService){
        this.activityService=activityService;
        this.topicService = topicService;
        this.sessionService = sessionService;
        this.registerService = registerService;
    }

   @GetMapping("/trang-chu-hoat-dong")
    public String showActivityOccured(Model model, HttpSession session)
    {

        Integer numberParticipants= activityService.getParticipants();
        Integer numberActivitys= activityService.getNumActivity();
//        List<Hoatdong> actiListUpcomming1 =activityService.getActivityUpcomming();
        List<Dangky> actiListUpcomming =activityService.getActivityUpcomming();
        List<Hoatdong> actiList = activityService.getActivityOccured();
        List<Hoatdong> actiListHappening= activityService.getActivityHappening();
        model.addAttribute("numberActivitys",numberActivitys);
//        model.addAttribute("Anh",new Anh());
        model.addAttribute("actiList",actiList);
        model.addAttribute("actiListHappening",actiListHappening);
        model.addAttribute(("actiListUpcomming"),actiListUpcomming);
        model.addAttribute("numberParticipants",numberParticipants);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
        return "TrangChuHoatDong";
    }
    @RequestMapping(value ="/Join")
    public String showActivityByID(Model model,@RequestParam("id") Integer id, HttpSession session)
    {
        Taikhoan taikhoan =activityService.getOrganizator(id);
        List<Thanhvien> thanhvienList =activityService.getMemberList(id);
        Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
        Hoatdong hoatdong = activityService.getActivityByID(id);
//        model.addAttribute("Anh",new Anh());
        model.addAttribute("hoatdong",hoatdong);
        model.addAttribute("taikhoan",taikhoan);
        model.addAttribute("thanhvien",thanhvien);
        model.addAttribute("thanhvienList",thanhvienList);
        sessionService.createSessionModel(model, session);
        return "ChiTietHoatDong";
    }

    @PostMapping("/addActivity")
    @ResponseBody
    public Map<String, Object> addActivity(@RequestParam("tenChuDe") String tenChuDe,
                                           @RequestParam("tenhd") String tenHD,
                                           @RequestParam("diaDiem") String diaDiem,
                                           @RequestParam("thoiGianBD") String thoiGianBD,
                                           @RequestParam("thoiGianKT") String thoiGianKT,
                                           @RequestParam("soTNVToiThieu") String sotnvtt,
                                           @RequestParam("soTNVToiDa") String sotnvtd,
                                           @RequestParam("moTa") String moTa,
                                           @RequestParam("anh") String anh,
                                           @RequestParam("imageFile") MultipartFile imageInput,
                                           @RequestParam("maTK") String userID, HttpSession session, Model model)
    {

        Map<String, Object> response = new HashMap<>();
        String fileName;
        String urlImageInDB;
        System.out.println("Đã vào COntroller");
        try {
            if (imageInput != null && !imageInput.isEmpty()) {
                System.out.println("Input image is not not not null");
                System.out.println("imageInput"+imageInput);
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

                // Kiểm tra xem tên file đã tồn tại hay chưa
                if (!Files.exists(newImagePath)) {
                    Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    // Nếu file đã tồn tại, kiểm tra xem nội dung có khác nhau hay không
                    byte[] newImageContent = imageInput.getBytes();
                    byte[] existingImageContent = Files.readAllBytes(newImagePath);

                    if (!Arrays.equals(newImageContent, existingImageContent)) {
                        fileName = "new" + fileName ;
                        newImagePath = uploadPath.resolve(fileName);
                        // Nếu nội dung khác nhau, lưu file mới và cập nhật đường dẫn ảnh
                        Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        // Nếu nội dung giống nhau, không cần thực hiện thêm hành động gì
                        System.out.println("Nội dung giống nhau, không cần lưu.");
                    }
                }
                // Cập nhật thông tin ảnh trong CSDL nếu cần
                System.out.println("Ten ...Controller:" + "/images/" + fileName);
                urlImageInDB = "/images/" + fileName;
                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
                Chude chude = topicService.getChuDeByTen(tenChuDe);
                Integer machude = chude.getId();
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, urlImageInDB, parseInt(userID));
            }
            sessionService.createSessionModel(model, session);
            response.put("message", "Thông báo: Thông tin đã được cập nhật thành công!");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            response.put("message", "Có lỗi xảy ra khi cập nhật thông tin thành viên.");
            response.put("success", false);
            return response;
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
//        Chude chude = topicService.getChuDeByTen(tenChuDe);
//        Integer machude = chude.getId();
////            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh, userID);
//        activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, userID);
//        ========================================
//        if(chude.getTenChuDe() !=null  )
//        {
//            Integer machude = chude.getId();
////            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh, userID);
//            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, userID);
//        }else {
//            topicService.addChude(chude);
//            Integer machude = topicService.getMaChuDeByTen(tenChuDe);
////            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, anh, userID);
//            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, userID);
//        }
//        sessionService.createSessionModel(model, session);
//        response.put("message", "Hoạt động đã được thêm mới thành công");
//        response.put("success", true);
//        return response;
    }

    @PostMapping(value="/Regis-activity")
    @ResponseBody
    public String JoinActivity(@RequestParam("maHD") Integer maHD,
                               @RequestParam("maTK") Integer maTK
                               )
    {
        System.out.println(maHD);
        System.out.println(maTK);
        registerService.joinActivity(maHD,maTK);
        return "redirect:/ChiTietHoatDong";
    }


//    @PostMapping("/addActivity")
////    @ResponseBody
//    public ResponseEntity<Map<String, Object>> insertTable(@ModelAttribute(value = "activity") Hoatdong hoatdong,
//                                                           @RequestParam(name = "maTK", required = false) Taikhoan id,
//                                                           @RequestParam(name = "tenChuDe") String tenChuDe,
//                                                           @RequestParam(value = "imageInputAdd", required = false) MultipartFile imageInput,
//                                                           RedirectAttributes redirectAttributes,
//                                                           HttpSession session, Model model) throws IOException, InterruptedException {
//
//        Map<String, Object> response = new HashMap<>();
//        String fileName;
//        System.out.println("Đã vào Controller");
//        try {
//            if (imageInput != null && !imageInput.isEmpty()) {
//                System.out.println("Input image is not not not null");
//                // Lấy tên tệp gốc
//                String anh = StringUtils.cleanPath(imageInput.getOriginalFilename());
//                var parts = anh.split("\\\\");// Sử dụng hàm split để tách đường dẫn thành các thành phần
//                fileName = parts[parts.length - 1];// Lấy phần tử cuối cùng trong mảng là tên tệp
//                // Giới hạn chiều dài của fileName thành 50 ký tự nếu nó dài hơn
//                int maxLength = 50;
//                if (fileName.length() > maxLength) {
//                    fileName = fileName.substring(0, maxLength);
//                }
//                System.out.println("Ten file anh Controller:" + fileName);
//                // Đường dẫn đầy đủ cho file mới
//                // Đường dẫn thư mục images trong resources/static
//                Path uploadPath = Paths.get("src", "main", "resources", "static", "images");
//                System.out.println("uploadPath:" + uploadPath);
//                // Tạo đường dẫn đầy đủ cho file mới
//                Path newImagePath = uploadPath.resolve(fileName);
//                System.out.println("newImagePath:" + newImagePath);
//                String urlImageInDB;
//                // Kiểm tra xem tên file đã tồn tại hay chưa
//                if (!Files.exists(newImagePath)) {
//                    Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
////                    urlImageInDB = "/images/" + fileName;
////                    thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
//                } else {
//                    // Nếu file đã tồn tại, kiểm tra xem nội dung có khác nhau hay không
//                    byte[] newImageContent = imageInput.getBytes();
//                    byte[] existingImageContent = Files.readAllBytes(newImagePath);
//
//                    if (!Arrays.equals(newImageContent, existingImageContent)) {
//                        fileName = "new" + fileName ;
//                        newImagePath = uploadPath.resolve(fileName);
//                        // Nếu nội dung khác nhau, lưu file mới và cập nhật đường dẫn ảnh
//                        Files.copy(imageInput.getInputStream(), newImagePath, StandardCopyOption.REPLACE_EXISTING);
////                        urlImageInDB = "/images/" + "new" + fileName;
////                        thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
//                    } else {
////                        urlImageInDB = "/images/" + fileName;
//                        // Nếu nội dung giống nhau, không cần thực hiện thêm hành động gì
//                        System.out.println("Nội dung giống nhau, không cần lưu.");
//                    }
//                }
//                // Cập nhật thông tin ảnh trong CSDL nếu cần
//                System.out.println("Ten ...Controller:" + "/images/" + fileName);
////        System.out.println("Ten file anh Controller:" + fileName);
////            foodManageBO.updateImageFood("/images/"+fileName, foodSaved.getId());
//
//                // Cập nhật đường dẫn ảnh trong đối tượng foodSaved
////                String urlImageInDB = "/images/" + fileName;
////            foodSaved.setAnhMon("/images/" + fileName);
//                urlImageInDB = "/images/" + fileName;
//                System.out.println("Ten file anh urlImageInDB:" + urlImageInDB);
////            return urlImageInDB;
////                thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
//                System.out.println("---------------------\n" + hoatdong.getAnh()+hoatdong.getTenhd()+hoatdong.getDiaDiem()+hoatdong.getMoTa()+hoatdong.getTenhd()+hoatdong.getLiDoHuy()+hoatdong.getMaChuDe()+hoatdong.getThoiGianBD());
//                System.out.println("----------------------\n" );
////                int soTNVToiThieu = Integer.parseInt(request.getParameter("soTNVToiThieu"));
////                int soTNVToiDa = Integer.parseInt(request.getParameter("soTNVToiDa"));
//
//                // Tiếp tục xử lý hoatdong và các thao tác khác...
////                hoatdong.setSoTNVToiThieu(soTNVToiThieu);
////                hoatdong.setSoTNVToiDa(soTNVToiDa);
//                hoatdong.setAnh(urlImageInDB);
//                Chude chude = topicService.getChuDeByTen(tenChuDe);
//                hoatdong.setMaChuDe(chude);
//                hoatdong.setTinhTrangHD((byte) 1);
//                hoatdong.setTinhTrangDuyet((byte) 0);
//                hoatdong.setLiDoHuy(null);
//                activityService.addMyActivity(hoatdong);
//                Dangky dangky = new Dangky();
//                dangky.setMaTK(id);
////                dangky.setMaTK((Taikhoan) session.getAttribute("account"));
//                dangky.setMaHD(hoatdong);
//                dangky.setThoiGianDK(Instant.now());
//                dangky.setPhanQuyen(true);
//                dangky.setTrangThai(true);
//               registerService.saveDK(dangky);
//            }
////        accountService.updateAvatar();
////            memberService.updateInfor(thanhvien);
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
