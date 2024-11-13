package com.hackathon.hackathon;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Map;
import jakarta.persistence.ElementCollection;

@Entity
public class Moodface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String mood;
    private float confidence;
    
    @ElementCollection
    private Map<String, Double> all_emotions;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public Map<String, Double> getAll_emotions() {
        return all_emotions;
    }

    public void setAll_emotions(Map<String, Double> all_emotions) {
        this.all_emotions = all_emotions;
    }
}

