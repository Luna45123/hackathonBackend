package com.dto;

import java.util.List;
import java.util.Map;

public class DashboardDTO {

    private Map<String, Map<String, Integer>> moodZoneCounts;
    private Map<String, Map<String, Integer>> timeBasedMoodZoneCounts;
    private List<DiaryDTO> diaries;

    public Map<String, Map<String, Integer>> getMoodZoneCounts() {
        return moodZoneCounts;
    }

    public void setMoodZoneCounts(Map<String, Map<String, Integer>> moodZoneCounts) {
        this.moodZoneCounts = moodZoneCounts;
    }

    public Map<String, Map<String, Integer>> getTimeBasedMoodZoneCounts() {
        return timeBasedMoodZoneCounts;
    }

    public void setTimeBasedMoodZoneCounts(Map<String, Map<String, Integer>> timeBasedMoodZoneCounts) {
        this.timeBasedMoodZoneCounts = timeBasedMoodZoneCounts;
    }

    public List<DiaryDTO> getDiaries() {
        return diaries;
    }

    public void setDiaries(List<DiaryDTO> diaries) {
        this.diaries = diaries;
    }
}
