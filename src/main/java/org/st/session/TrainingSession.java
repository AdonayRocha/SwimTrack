package org.st.session;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "training_sessions")
public class TrainingSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    @NotNull(message = "Data é obrigatória")
    @PastOrPresent(message = "Data não pode ser no futuro")
    private LocalDateTime date;
    
    @Column(nullable = false)
    @NotNull(message = "Duração é obrigatória")
    @Min(value = 1, message = "Duração deve ser pelo menos 1 minuto")
    @Max(value = 1440, message = "Duração não pode exceder 24 horas")
    private int duration;
    
    @Column(nullable = false)
    @NotNull(message = "Distância é obrigatória")
    @DecimalMin(value = "0.1", message = "Distância deve ser pelo menos 0.1 metros")
    @DecimalMax(value = "100000.0", message = "Distância não pode exceder 100km")
    private double distance;
    
    @Column(nullable = false)
    @NotBlank(message = "Tipo de treino é obrigatório")
    @Size(max = 100, message = "Tipo de treino não pode exceder 100 caracteres")
    private String type;
    
    @Column(length = 1000)
    @Size(max = 1000, message = "Notas não podem exceder 1000 caracteres")
    private String notes;

    public TrainingSession() {}

    public TrainingSession(LocalDateTime date, int duration, double distance, String type, String notes) {
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.type = type;
        this.notes = notes;
    }

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