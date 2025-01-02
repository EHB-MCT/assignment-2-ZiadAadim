package ehb.be.truedatatracking_ziadaadim.controllers

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import ehb.be.truedatatracking_ziadaadim.services.UserEventService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/events")
class UserEventController(private val userEventService: UserEventService) {

    @PostMapping("/log")
    fun logEvent(@RequestBody userEvent: UserEvent): String {
        userEventService.logEvent(userEvent)
        return "Event logged successfully!"
    }

    @GetMapping("/all")
    fun getAllEvents(): List<UserEvent> {
        return userEventService.getAllEvents()
    }

    @PostMapping("/anatomy/hands")
    fun logHandsClick(): String {
        val dummyEvent = UserEvent(
            userId = "dummy_user", // Replace with actual user ID logic later
            eventType = "BUTTON_CLICK",
            page = "hands",
            buttonClicked = "hands_section",
            timestamp = LocalDateTime.now()
        )
        userEventService.logEvent(dummyEvent)
        return "Hands section click logged!"
    }
}
