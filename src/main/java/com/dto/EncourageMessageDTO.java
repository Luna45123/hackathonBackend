package com.dto;

public class EncourageMessageDTO {

    private Long id;
    private String author;
    private String message;
    private String mood;
    private String moodZone;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getMoodZone() {
        return moodZone;
    }

    public void setMoodZone(String moodZone) {
        this.moodZone = moodZone;
    }
}
