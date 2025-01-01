package ehb.be.truedatatracking_ziadaadim

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CourseController(val repository: InteractionRepository) {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("courses", listOf("Heads", "Torso", "Arms", "Legs"))
        return "home"
    }

    @PostMapping("/track")
    fun trackData(@RequestParam action: String, @RequestParam module: String): String {
        repository.save(Interaction(action = action, module = module))
        return "redirect:/"
    }
}
