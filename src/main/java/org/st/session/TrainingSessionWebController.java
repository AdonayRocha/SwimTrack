package org.st.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class TrainingSessionWebController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    // Exibe o formulário de cadastro
    @GetMapping("/sessions/new")
    public String showSessionForm() {
        return "session-form"; // Renderiza session-form.html
    }

    // Processa o envio do formulário
    @PostMapping("/sessions")
    public String createSession(
        @RequestParam("date") String date,
        @RequestParam("duration") int duration,
        @RequestParam("distance") double distance,
        @RequestParam("type") String type,
        @RequestParam("notes") String notes
    ) {
        TrainingSession session = new TrainingSession();
        session.setDate(LocalDateTime.parse(date));
        session.setDuration(duration);
        session.setDistance(distance);
        session.setType(type);
        session.setNotes(notes);

        trainingSessionService.save(session);

        return "redirect:/sessions";
    }

    // Exibe a lista de sessões
    @GetMapping("/sessions")
    public String listSessions(Model model) {
        model.addAttribute("sessions", trainingSessionService.listAll());
        return "sessions"; // Renderiza sessions.html
    }
}