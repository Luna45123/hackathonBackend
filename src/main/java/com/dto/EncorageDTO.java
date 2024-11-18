package com.dto;

public class EncorageDTO {
    private Long id;
    private String name;
    private String description;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "EncorageDTO [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}

