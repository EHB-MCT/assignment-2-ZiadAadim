package ehb.be.truedatatracking_ziadaadim.controllers

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import ehb.be.truedatatracking_ziadaadim.services.UserEventService
import org.springframework.web.bind.annotation.*
import jakarta.servlet.http.HttpSession

@RestController
@RequestMapping("/api/events")
class UserEventController(
    private val userEventService: UserEventService,
) {

    @PostMapping("/log")
    fun logEvent(@RequestBody userEvent: UserEvent): String {
        userEventService.logEvent(userEvent)
        return "Event logged successfully!"
    }

    @GetMapping("/all")
    fun getAllEvents(): List<UserEvent> {
        return userEventService.getAllEvents()
    }

    @PostMapping("/accept-terms")
    fun acceptTerms(@RequestBody userIdPayload: Map<String, String>, session: HttpSession): String {
        val userId = userIdPayload["userId"] ?: return "Failed to receive user ID!"
        session.setAttribute("userId", userId)
        return "User ID registered successfully!"
    }

    @PostMapping("/log-with-session")
    fun logEventWithSession(@RequestBody userEvent: UserEvent, session: HttpSession): String {
        val userId = session.getAttribute("userId")?.toString() ?: return "User not logged in!"

        val updatedEvent = userEvent.copy(userId = userId)
        userEventService.logEvent(updatedEvent)
        return "Event logged successfully!"
    }
}

