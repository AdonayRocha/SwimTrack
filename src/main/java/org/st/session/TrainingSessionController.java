package org.st.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @GetMapping
    public ResponseEntity<List<TrainingSession>> listAll() {
        try {
            List<TrainingSession> sessions = trainingSessionService.listAll();
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingSession> getById(@PathVariable String id) {
        try {
            TrainingSession session = trainingSessionService.getById(id);
            return session != null ? ResponseEntity.ok(session) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<TrainingSession> create(@RequestBody TrainingSession session) {
        try {
            TrainingSession savedSession = trainingSessionService.save(session);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSession);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TrainingSession> update(@PathVariable String id, @RequestBody TrainingSession session) {
        try {
            if (!trainingSessionService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            session.setId(id);
            TrainingSession updatedSession = trainingSessionService.save(session);
            return ResponseEntity.ok(updatedSession);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            if (!trainingSessionService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            trainingSessionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}