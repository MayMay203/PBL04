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
}
