package org.st.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "training_sessions")
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(nullable = false)
    private int duration; // Minutos
    
    @Column(nullable = false)
    private double distance; // Metros
    
    @Column(nullable = false)
    private String type;
    
    @Column(length = 1000)
    private String notes;

    // Getters
    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public String getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}