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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonalityController {
    private final SessionService sessionService;
    private final ActivityService activityService;
    private final MemberService memberService;
    private final ImageProcessorService imageProcessorService;
    private final AccountService accountService;
    private final NotificationService notificationService;

    private final TopicService topicService;
    private final SummaryService summaryService;
    private final RegisterService registerService;
    private final EvaluationService evaluationService;

    @Autowired
    public PersonalityController(SessionService sessionService, ActivityService activityService,
                                 MemberService memberService, ImageProcessorService imageProcessorService, AccountService accountService, TopicService topicService, SummaryService summaryService, RegisterService registerService, NotificationService notificationService, EvaluationService evaluationService) {
        this.sessionService = sessionService;
        this.activityService = activityService;
        this.memberService = memberService;
        this.imageProcessorService = imageProcessorService;
        this.accountService = accountService;
        this.topicService = topicService;
        this.summaryService = summaryService;
        this.registerService = registerService;
        this.notificationService = notificationService;
        this.evaluationService = evaluationService;
    }
    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    private List<Hoatdong> actListIsHost = new ArrayList<>();
    private List<Hoatdong> actListIsMember = new ArrayList<>();
    private List<Integer> listSummaryIsHostActi = new ArrayList<>();
    private List<Dangky> listRegisIsHostActi = new ArrayList<>();
    private boolean isFilter = false;
    @GetMapping("/hd-trang-ca-nhan")
    public String createSession(Model model, HttpSession session,@ModelAttribute("message") String message,
                                @RequestParam(name = "account_ID") Integer accountID){
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("listTopics", listTopics);
        sessionService.createSessionModel(model, session);
        showTrangCaNhan(model, accountID, message, session);

        return "HoatDongTrangCaNhan";
    }

    @PostMapping("/bo-loc-hoat-dong")
    @ResponseBody
        public Map<String, Object> showHaveFilter(@RequestParam(required = false) Integer selectedValue,
                                                  @ModelAttribute("message") String message,
                                                  @RequestParam Integer accountID, RedirectAttributes redirectAttributes, Model model, HttpSession session){
        Map<String, Object> response = new HashMap<>();
        isFilter = true;
        Thanhvien user = memberService.findMemberByID(accountID);
        //tình trạng hoạt động
        switch (selectedValue){
            case -3:
                actListIsHost = activityService.getAllActivityIsHost(accountID);
                actListIsMember = activityService.getAllActivityIsMember(accountID);
                break;
            case -2:
                actListIsHost = activityService.getAllActivityForFilter(accountID, true, 0, 0);
                actListIsMember = activityService.getAllActivityForFilter(accountID, false, 0, 0);
                break;
            case -1:
                actListIsHost = activityService.getAllActivityForFilter(accountID, true, -1, -1);
                actListIsMember = activityService.getAllActivityForFilter(accountID, false, -1, -1);
                break;
            case 0:
                actListIsHost = activityService.getAllActivityForFilters(accountID, true, 0, 1);
                actListIsMember = activityService.getAllActivityForFilters(accountID, false, 0, 1);
                break;
            case 1:
                actListIsHost = activityService.getAllActivityForFilter(accountID, true, 1, 2);
                actListIsMember = activityService.getAllActivityForFilter(accountID, false, 1, 2);
                break;
            case 2:
                actListIsHost = activityService.getAllActivityForFilter(accountID, true, 2, 2);
                actListIsMember = activityService.getAllActivityForFilter(accountID, false, 2, 2);
                break;
            case 12:// đã tổng kết:
                actListIsHost = activityService.getAllActiForFilterSummaried(accountID, true, 2, 2);
                actListIsMember = activityService.getAllActiForFilterSummaried(accountID, false, 2, 2);
                break;
            case 13://chưa tổng kết:
                actListIsHost = activityService.getAllActiForFilterNoSummary(accountID, true, 0, 1);
                actListIsMember = activityService.getAllActiForFilterNoSummary(accountID, false, 0, 1);
                break;
        }

        redirectAttributes.addFlashAttribute("hideModal", true);
        response.put("user", user);
        response.put("success", true);
        showTrangCaNhan(model, accountID, message, session);
        return response;
    }
    public void getListSummaryIsHostActi(List<Hoatdong> actList){
        listSummaryIsHostActi.clear();
        listRegisIsHostActi.clear();
        for(Hoatdong hd : actList){
            //lấy đăng ký:
            listRegisIsHostActi.add(activityService.getRegisterInfo(hd.getId()));
            //lấy tổng kết
            if(summaryService.getSummaryByID(hd.getId()) != null){
                listSummaryIsHostActi.add(summaryService.getSummaryByID(hd.getId()).getId());
            }
            else{
                listSummaryIsHostActi.add(-1);
            }
        }
    }
    public void showTrangCaNhan(Model model,
                       @RequestParam(name="id") Integer id,
                       @ModelAttribute("message") String message,
                       HttpSession session) {
//        actListIsHost = activityService.getAllActivityIsHost(id);
        model.addAttribute("actListIsHost", actListIsHost);
        getListSummaryIsHostActi(actListIsHost);


        model.addAttribute("listRegisIsHostActi",listRegisIsHostActi);
        model.addAttribute("listSummaryIsHostActi", listSummaryIsHostActi);

//        actListIsMember = activityService.getAllActivityIsMember(id);
        model.addAttribute("actListIsMember", actListIsMember);
        List<Thanhvien> listInforOfHostActi = new ArrayList<>();
        List<Dangky> listInforOfActiJoin = new ArrayList<>();
        List<Dangky> listRegisIsMemberActi = new ArrayList<>();
        List<Integer> listEvalueIsMemberActi = new ArrayList<>();
        List<Integer> listSummaryIsMemberActi = new ArrayList<>();
        for(Hoatdong hd : actListIsMember){
            //lấy thông tin của tổ chức hoạt động
            listInforOfHostActi.add(registerService.getInforOfHostActi(activityService.getRegisterInfo(hd.getId()).getMaTK()));
            //lấy đăng ký:
            listInforOfActiJoin.add(activityService.getRegisterInfo(hd.getId()));
            //lấy từ đăng ký thông tin người vai trò là tham gia
            listRegisIsMemberActi.add(registerService.getRegisterIsMember(hd.getId(), id));

            if(evaluationService.getEvaluationByIDHDTK(hd.getId(), id) != null){
                listEvalueIsMemberActi.add(evaluationService.getRateEvaluationByIDHDTK(hd.getId(), id));
            }
            else{
                listEvalueIsMemberActi.add(-1);
            }
            //lấy tổng kết
            if(summaryService.getSummaryByID(hd.getId()) != null){
                listSummaryIsMemberActi.add(summaryService.getSummaryByID(hd.getId()).getId());
            }
            else{
                listSummaryIsMemberActi.add(-1);
            }

        }
        model.addAttribute("listInforOfHostActi", listInforOfHostActi);
        model.addAttribute("listInforOfActiJoin", listInforOfActiJoin);
        model.addAttribute("listRegisIsMemberActi", listRegisIsMemberActi);
        model.addAttribute("listEvalueIsMemberActi",listEvalueIsMemberActi);
        model.addAttribute("listSummaryIsMemberActi", listSummaryIsMemberActi);

        model.addAttribute("activity", new Hoatdong());
//        Dangky getRegisterInfo = activityService.getRegisterInfo(id)
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("listTopics", listTopics);

        sessionService.createSessionModel(model, session);
        // Truyền thông điệp từ RedirectAttributes đến model
        model.addAttribute("message", message);

        //Hiển thị nội dung thông báo
        Taikhoan myAcc = (Taikhoan) session.getAttribute("account");
        if(myAcc!=null){
            List<Thongbao> listNotice = new ArrayList<>();
            listNotice = notificationService.getNotifiByIdAcc(myAcc.getId());
            model.addAttribute("listNotice",listNotice);
            //Đếm thông báo chưa đọc
            Integer numOfNotice = notificationService.countNotice(myAcc.getId());
            model.addAttribute("numOfNotice",numOfNotice);
        }
//        return "TrangCaNhan";
    }
    @GetMapping("/trang-ca-nhan")
    public String show(Model model,
                       @RequestParam(name="id") Integer id,
                       @ModelAttribute("message") String message,
                       HttpSession session) {

            actListIsHost = activityService.getAllActivityIsHost(id);
            actListIsMember = activityService.getAllActivityIsMember(id);
        showTrangCaNhan(model, id, message,  session);
        return "TrangCaNhan";
    }

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
                        System.out.println("Nội dung giống nhau, không cần lưu.");
                    }
                }
                urlImageInDB = "/images/" + fileName;
                thanhvien.getMaTK().setAnhDaiDien(urlImageInDB);
            }
            memberService.updateInfor(thanhvien);
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
}
