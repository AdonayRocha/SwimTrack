package org.st.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionService {

    @Autowired
    private TrainingSessionRepository repository;

    public List<TrainingSession> listAll() {
        return repository.findAll();
    }

    public TrainingSession getById(String id) {
        Optional<TrainingSession> session = repository.findById(id);
        return session.orElse(null);
    }

    public TrainingSession save(TrainingSession session) {
        if (session.getId() == null || session.getId().isEmpty()) {
            session.setId(java.util.UUID.randomUUID().toString());
        }
        return repository.save(session);
    }
    
    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}