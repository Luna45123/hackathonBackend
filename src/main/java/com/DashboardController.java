package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DashboardDTO;
import com.dto.DiaryDTO;
import com.mapper.DiaryMapper;
import com.repository.DiaryRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

@RestController
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    // Other methods...

    @GetMapping("/dashboard/user/email/{email}")
    public ResponseEntity<DashboardDTO> getDashboardByEmail(@PathVariable String email) {
        List<DiaryDTO> userDiaries = diaryMapper.toDiaryDTOList(diaryRepository.findByUserEmail(email));

        if (!userDiaries.isEmpty()) {
            DashboardDTO dashboardDTO = new DashboardDTO();

            // Mood zone counts
            Map<String, Map<String, Integer>> moodZoneCounts = new HashMap<>();
            // Daily mood zone counts
            Map<String, Map<String, Integer>> dailyMoodZoneCounts = new TreeMap<>();

            for (DiaryDTO diary : userDiaries) {
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
            dashboardDTO.setDiaries(userDiaries);

            return new ResponseEntity<>(dashboardDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
