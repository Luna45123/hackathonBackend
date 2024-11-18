package com;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DashboardDTO;
import com.dto.DiaryDTO;
import com.mapper.DiaryMapper;
import com.repository.DiaryRepository;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> getDashboardData() {
        DashboardDTO dashboardDTO = new DashboardDTO();

        // Retrieve all diaries and map to DTOs
        List<DiaryDTO> diaryDTOs = diaryMapper.toDiaryDTOList(diaryRepository.findAll());

        // Mood zone counts
        Map<String, Map<String, Integer>> moodZoneCounts = new HashMap<>();

        // Daily mood zone counts
        Map<String, Map<String, Integer>> dailyMoodZoneCounts = new TreeMap<>();

        for (DiaryDTO diary : diaryDTOs) {
            String moodZone = diary.getMoodZone();
            String mood = diary.getMood();
            String day = diary.getTime().toLocalDate().toString(); // Group by YYYY-MM-DD

            // Update moodZoneCounts
            moodZoneCounts.putIfAbsent(moodZone, new HashMap<>());
            moodZoneCounts.get(moodZone).put(mood, 
                moodZoneCounts.get(moodZone).getOrDefault(mood, 0) + 1);

            // Update daily counts for each moodZone and each date
            dailyMoodZoneCounts.putIfAbsent(day, new HashMap<>());
            Map<String, Integer> moodCountsForDay = dailyMoodZoneCounts.get(day);
            moodCountsForDay.put(moodZone, 
                moodCountsForDay.getOrDefault(moodZone, 0) + 1);
        }

        // Populate DTO
        dashboardDTO.setMoodZoneCounts(moodZoneCounts);
        dashboardDTO.setTimeBasedMoodZoneCounts(dailyMoodZoneCounts);
        dashboardDTO.setDiaries(diaryDTOs);

        return new ResponseEntity<>(dashboardDTO, HttpStatus.OK);
    }
}


