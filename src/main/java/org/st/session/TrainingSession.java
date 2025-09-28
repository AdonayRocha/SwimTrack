package org.st.session;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    // Construtor padrão
    public TrainingSession() {}

    // Construtor com parâmetros
    public TrainingSession(LocalDateTime date, int duration, double distance, String type, String notes) {
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.type = type;
        this.notes = notes;
    }

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

    @Override
    public String toString() {
        return "TrainingSession{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", distance=" + distance +
                ", type='" + type + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}