package com;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
@CrossOrigin(origins = "*") // Allows all origins to access this controller
public class DashboardController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    @GetMapping("/dashboard")
public ResponseEntity<DashboardDTO> getDashboardData() {
    DashboardDTO dashboardDTO = new DashboardDTO();

    // Retrieve all diaries and map to DTOs using the mapper
    List<DiaryDTO> diaryDTOs = diaryMapper.toDiaryDTOList(diaryRepository.findAll());

    // Initialize moodZoneCounts map
    Map<String, Map<String, Integer>> moodZoneCounts = new HashMap<>();

    // Initialize monthly mood zone counts, using TreeMap to keep months in order
    Map<String, Map<String, Integer>> monthlyMoodZoneCounts = new TreeMap<>();

    // Iterate over each diary entry and populate moodZoneCounts and monthly counts
    for (DiaryDTO diary : diaryDTOs) {
        String moodZone = diary.getMoodZone();
        String mood = diary.getMood();
        String month = diary.getTime().getYear() + "-" + String.format("%02d", diary.getTime().getMonthValue()); // Format: YYYY-MM

        // Initialize the mood map for the moodZone if absent
        moodZoneCounts.putIfAbsent(moodZone, new HashMap<>());
        monthlyMoodZoneCounts.putIfAbsent(month, new HashMap<>());

        // Increment the mood count for the specific moodZone and mood
        Map<String, Integer> moodCounts = moodZoneCounts.get(moodZone);
        moodCounts.put(mood, moodCounts.getOrDefault(mood, 0) + 1);

        // Increment the count in monthly data for each mood zone
        Map<String, Integer> monthlyCounts = monthlyMoodZoneCounts.get(month);
        monthlyCounts.put(moodZone, monthlyCounts.getOrDefault(moodZone, 0) + 1);
    }

    // Set counts and diaries in the dashboard DTO
    dashboardDTO.setMoodZoneCounts(moodZoneCounts);
    dashboardDTO.setTimeBasedMoodZoneCounts(monthlyMoodZoneCounts); // New field for monthly data
    dashboardDTO.setDiaries(diaryDTOs);

    return new ResponseEntity<>(dashboardDTO, HttpStatus.OK);
}

}
