package ehb.be.truedatatracking_ziadaadim

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CourseController(val repository: InteractionRepository) {

    @GetMapping("/welcome")
    fun welcome(): String {
        return "welcome"
    }

    @GetMapping("/main")
    fun mainPage(): String {
        return "main"
    }

    @GetMapping("/course/anatomy")
    fun anatomyPage(): String {
        return "anatomy"
    }

    @GetMapping("/course/perspective")
    fun perspectivePage(): String {
        return "perspective"
    }

    @GetMapping("/course/gesture")
    fun gesturePage(): String {
        return "gesture"
    }

    @GetMapping("/gesture/timed")
    fun timedGesturePage(): String {
        return "gesture-timed"
    }

    @GetMapping("/course/reference")
    fun referencePage(): String {
        return "reference"
    }
}
