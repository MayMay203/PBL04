package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.RegisterService;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.TopicService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("actiList",actiList);
        model.addAttribute("actiListHappening",actiListHappening);
        model.addAttribute(("actiListUpcomming"),actiListUpcomming);
        model.addAttribute("numberParticipants",numberParticipants);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
        return "TrangChuHoatDong";
    }
    @RequestMapping(value ="/Join")
    public String showActivityByID(Model model,@RequestParam("id") Integer id,
                                   @RequestParam Map<String, String> params,
                                   HttpSession session)
    {
        sessionService.createSessionModel(model, session);
        params = params.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> URLDecoder.decode(entry.getValue(), StandardCharsets.UTF_8)
                ));
        Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
        Dangky checkDangky = registerService.getDangKyByHDTK(myaccount.getId(), id);
        Taikhoan taikhoan =activityService.getOrganizator(id);
        List<Thanhvien> thanhvienList =activityService.getMemberList(id);
        Thanhvien  thanhvien=activityService.getMemberByID(taikhoan.getId());
        Hoatdong hoatdong = activityService.getActivityByID(id);
        model.addAttribute("hoatdong",hoatdong);
        model.addAttribute("taikhoan",taikhoan);
        model.addAttribute("thanhvien",thanhvien);
        model.addAttribute("thanhvienList",thanhvienList);
        model.addAttribute("checkDangky",checkDangky);
        return "ChiTietHoatDong";
    }
//    @GetMapping("/them-hoat-dong")
//    public String showModalThemHoatDong(Model model, HttpSession session){
//        model.addAttribute("activity", new Hoatdong());
//        sessionService.createSessionModel(model, session);
//        return  "ThemHoatDong";
//    }

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
            activityService.addActivity(machude, tenHD, diaDiem, thoiGianBD, thoiGianKT, sotnvtt, sotnvtd, moTa, urlImageInDB,  parseInt(userID));
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
    @GetMapping("/get-activity-by-id")
    @ResponseBody
    public Hoatdong getActivityByID(@RequestParam("maHD") Integer maHD)
    {
        Hoatdong hd = activityService.getActivityByID(maHD);
        return hd;
    }

//    @GetMapping("/hoat-do/{IdTitle}")
//    @ResponseBody
//    public ResponseEntity<List<Dexuat>> showSuggestionByTitle(@PathVariable(name = "IdTitle", required = false) Integer IdTitle) {
//       // String str = title.replaceAll("-"," ");
//       // System.out.println(str);
//        List<Dexuat> suggestionList = suggestionService.getSuggestionByIdTitle(IdTitle);
//        return ResponseEntity.ok(suggestionList);
//    }
}
