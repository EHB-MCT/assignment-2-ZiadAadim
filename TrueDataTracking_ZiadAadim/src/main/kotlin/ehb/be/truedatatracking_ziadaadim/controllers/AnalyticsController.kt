package ehb.be.truedatatracking_ziadaadim.controllers

import ehb.be.truedatatracking_ziadaadim.services.UserEventService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/analytics")
class AnalyticsController(private val userEventService: UserEventService) {

    // Endpoint to get navigation path for a specific user
    @GetMapping("/user/{userId}/navigation-path")
    fun getUserNavigationPath(@PathVariable userId: String): List<Pair<String, Long>> {
        return userEventService.getUserNavigationPath(userId)
    }

    // Endpoint to get 'go back' clicks for a specific user
    @GetMapping("/user/{userId}/go-back-clicks")
    fun getGoBackClicks(@PathVariable userId: String): List<String> {
        return userEventService.getGoBackClicks(userId)
    }

    // Endpoint to get aggregated data for all users
    @GetMapping("/aggregated")
    fun getAggregatedData(): Map<String, Any> {
        return userEventService.getAggregatedData()
    }

    @DeleteMapping("/clear")
    fun clearDatabase(): String {
        userEventService.clearAllEvents()
        return "All events cleared!"
    }

    @GetMapping("/user-stats")
    fun getUserStats(): Map<String, Any> {
        val userIds = userEventService.getAllUserIds()
        return mapOf(
            "userCount" to userIds.size,
            "userIds" to userIds
        )
    }

    @GetMapping("/time-spent")
    fun getTimeSpentData(): Map<String, Long> {
        return userEventService.getTimeSpentForSpecificPages()
    }

    @GetMapping("/navigation-path")
    fun getNavigationPathData(): Map<String, Int> {
        return userEventService.getFirstNavigationPaths()
    }

    @GetMapping("/go-back-rates")
    fun getGoBackRatesData(): Map<String, Int> {
        return userEventService.getGoBackActions()
    }
}