package org.st.domain;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingSession {
    private String id;
    private LocalDateTime date;
    private int duration; // Minutos
    private double distance; // Metros
    private String type;
    private String notes;
}