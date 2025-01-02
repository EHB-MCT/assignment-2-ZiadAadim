package ehb.be.truedatatracking_ziadaadim.services

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import ehb.be.truedatatracking_ziadaadim.repositories.UserEventRepository
import org.springframework.stereotype.Service

@Service
class UserEventService(private val userEventRepository: UserEventRepository) {
    fun logEvent(userEvent: UserEvent): UserEvent {
        return userEventRepository.save(userEvent)
    }

    fun getAllEvents(): List<UserEvent> {
        return userEventRepository.findAll()
    }
}