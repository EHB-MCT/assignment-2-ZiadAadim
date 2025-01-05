package ehb.be.truedatatracking_ziadaadim.services

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import ehb.be.truedatatracking_ziadaadim.repositories.UserEventRepository
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime

@Service
class UserEventService(private val userEventRepository: UserEventRepository) {
    fun logEvent(userEvent: UserEvent): UserEvent {
        return userEventRepository.save(userEvent)
    }

    fun getAllEvents(): List<UserEvent> {
        return userEventRepository.findAll()
    }

    fun getUserNavigationPath(userId: String): List<Pair<String, Long>> {
        val events = userEventRepository.findAllByUserId(userId).sortedBy { it.timestamp }
        val navigationPath = mutableListOf<Pair<String, Long>>()

        var previousTimestamp: LocalDateTime? = null
        for (event in events) {
            if (previousTimestamp != null && event.page != null) { // Check for null page
                val timeSpent = Duration.between(previousTimestamp, event.timestamp).seconds
                navigationPath.add(Pair(event.page, timeSpent))
            }
            previousTimestamp = event.timestamp
        }
        return navigationPath
    }

    fun getGoBackClicks(userId: String): List<String> {
        val events = userEventRepository.findAllByUserId(userId)
        return events.filter { it.buttonClicked == "Go Back" && it.page != null }
            .map { it.page!! }
    }

    fun getAggregatedData(): Map<String, Any> {
        val allEvents = userEventRepository.findAllEvents()

        val totalNavigationPaths = allEvents.groupBy { it.userId }.mapValues { entry ->
            entry.value.sortedBy { it.timestamp }.map { it.page }
        }

        val timeSpentPerPage = allEvents.groupBy { it.page }.mapValues { entry ->
            entry.value.sumOf { it.timeSpent ?: 0L }
        }

        val goBackClicksPerPage = allEvents.filter { it.buttonClicked == "Go Back" }
            .groupBy { it.page }
            .mapValues { entry -> entry.value.size }

        return mapOf(
            "navigationPaths" to totalNavigationPaths,
            "timeSpentPerPage" to timeSpentPerPage,
            "goBackClicksPerPage" to goBackClicksPerPage
        )
    }

    fun clearAllEvents() {
        userEventRepository.deleteAll()
    }

    fun getAllUserIds(): List<String> {
        return userEventRepository.findAll().map { it.userId }.distinct()
    }



    fun getTimeSpentForSpecificPages(): Map<String, Long> {
        val allowedPages = setOf("Anatomy", "Gesture", "Perspective")
        return userEventRepository.findAll()
            .filter { it.page != null && it.page in allowedPages } // Exclude null pages and filter for allowed pages
            .groupBy { it.page!! } // Use non-null assertion after filtering nulls
            .mapValues { entry ->
                entry.value.sumOf { it.timeSpent ?: 0L } // Sum the time spent for each page
            }
    }



}