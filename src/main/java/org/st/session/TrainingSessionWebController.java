package org.st.session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sessions")
public class TrainingSessionWebController {

    private final TrainingSessionService trainingSessionService;

    public TrainingSessionWebController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @GetMapping
    public String listSessions(Model model) {
        try {
            model.addAttribute("sessions", trainingSessionService.listAll());
            return "sessions";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar sessões de treino: " + e.getMessage());
            return "sessions";
        }
    }

    @GetMapping("/new")
    public String showSessionForm(Model model) {
        model.addAttribute("session", new TrainingSession());
        model.addAttribute("isEdit", false);
        return "session-form";
    }

    @PostMapping
    public String createSession(@Valid @ModelAttribute TrainingSession session, 
                               BindingResult result, 
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "session-form";
        }

        try {
            trainingSessionService.save(session);
            redirectAttributes.addFlashAttribute("success", "Sessão de treino criada com sucesso!");
            return "redirect:/sessions";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao criar sessão: " + e.getMessage());
            return "redirect:/sessions";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditSession(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            TrainingSession session = trainingSessionService.getById(id);
            if (session == null) {
                redirectAttributes.addFlashAttribute("error", "Sessão de treino não encontrada!");
                return "redirect:/sessions";
            }
            model.addAttribute("session", session);
            model.addAttribute("isEdit", true);
            return "session-form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao carregar sessão: " + e.getMessage());
            return "redirect:/sessions";
        }
    }

    @PostMapping("/edit/{id}")
    public String editSession(@PathVariable String id, 
                             @Valid @ModelAttribute TrainingSession session,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "session-form";
        }

        try {
            if (!trainingSessionService.existsById(id)) {
                redirectAttributes.addFlashAttribute("error", "Sessão de treino não encontrada!");
                return "redirect:/sessions";
            }
            
            session.setId(id);
            trainingSessionService.save(session);
            redirectAttributes.addFlashAttribute("success", "Sessão de treino atualizada com sucesso!");
            return "redirect:/sessions";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar sessão: " + e.getMessage());
            return "redirect:/sessions";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteSession(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            if (!trainingSessionService.existsById(id)) {
                redirectAttributes.addFlashAttribute("error", "Sessão de treino não encontrada!");
                return "redirect:/sessions";
            }
            
            trainingSessionService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Sessão de treino excluída com sucesso!");
            return "redirect:/sessions";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir sessão: " + e.getMessage());
            return "redirect:/sessions";
        }
    }

    @GetMapping("/{id}")
    public String viewSession(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            TrainingSession session = trainingSessionService.getById(id);
            if (session == null) {
                redirectAttributes.addFlashAttribute("error", "Sessão de treino não encontrada!");
                return "redirect:/sessions";
            }
            model.addAttribute("session", session);
            return "session-details";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao carregar sessão: " + e.getMessage());
            return "redirect:/sessions";
        }
    }
}