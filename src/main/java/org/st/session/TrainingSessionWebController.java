package org.st.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrainingSessionWebController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @GetMapping("/sessions")
    public String listSessions(Model model) {
        model.addAttribute("sessions", trainingSessionService.listAll());
        return "sessions";
    }

    @GetMapping("/sessions/new")
    public String showSessionForm(Model model) {
        model.addAttribute("session", new TrainingSession());
        return "session-form";
    }

    @PostMapping("/sessions")
    public String createSession(@ModelAttribute TrainingSession session) {
        trainingSessionService.save(session);
        return "redirect:/sessions";
    }

    @GetMapping("/sessions/edit/{id}")
    public String showEditSession(@PathVariable String id, Model model) {
        TrainingSession session = trainingSessionService.getById(id);
        model.addAttribute("session", session);
        return "session-form";
    }

    @PostMapping("/sessions/edit/{id}")
    public String editSession(@PathVariable String id, @ModelAttribute TrainingSession session) {
        session.setId(id);
        trainingSessionService.save(session);
        return "redirect:/sessions";
    }

    @PostMapping("/sessions/delete/{id}")
    public String deleteSession(@PathVariable String id) {
        trainingSessionService.deleteById(id);
        return "redirect:/sessions";
    }
}