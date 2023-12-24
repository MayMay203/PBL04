package com.example.pbl04.service;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.entity.Hoatdong;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledTasks {
    private final ActivityRepository activityRepository;

    public ScheduledTasks(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkActivityStatus() {
        List<Hoatdong> activities = activityRepository.getAllActivityNotHappening();
        LocalDate currentDate = LocalDate.now();
        for (Hoatdong activity : activities) {
            if (currentDate.equals(activity.getThoiGianBD())) {
                activityRepository.updateTrangThaiByNgayAndTrangThai(activity.getId());
            }
        }
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void CancelPostOverdue()
    {
        List<Hoatdong> activities = activityRepository.getAllActiviyPost();

        for(Hoatdong activity : activities)
        {
            LocalDate currentDate = LocalDate.now();
            if(currentDate.isAfter(activity.getThoiGianBD())){ // Nếu đến ngày nhưng post chưa được duyệt
                activityRepository.CancelActivity(activity.getId(),"Hủy do không được duyệt bài đăng");
            }
        }
    }
    @Scheduled(cron="0 0 0 * * ?")
    public void CancelActivityOverdue()
    {
        List<Hoatdong> activities = activityRepository.getAllActivityNotHappening();
        for(Hoatdong activity : activities)
        {
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = activity.getThoiGianBD();
            LocalDate sevenDaysBefore = startDate.minusDays(7);
            if (currentDate.equals(sevenDaysBefore) || currentDate.isAfter(sevenDaysBefore)) {
                activityRepository.CancelActivity(activity.getId(),"Hủy do người tổ chức không duyệt hoạt động");
            }
        }
    }
    @Scheduled(cron="0 0 * * * ?")
    public void TransActivityToFinish()
    {
        List<Hoatdong> activities = activityRepository.getActivityHappening();
        for(Hoatdong activity : activities)
        {
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(activity.getThoiGianKT())) {
               activityRepository.TransActivityToFinish(activity.getId());
            }
        }
    }
}
