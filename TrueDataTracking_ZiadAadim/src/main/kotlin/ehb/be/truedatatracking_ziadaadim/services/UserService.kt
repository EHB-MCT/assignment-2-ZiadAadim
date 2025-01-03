package ehb.be.truedatatracking_ziadaadim.services

import ehb.be.truedatatracking_ziadaadim.models.User
import ehb.be.truedatatracking_ziadaadim.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(sessionId: String): User {
        val newUser = User(sessionId = sessionId)
        return userRepository.save(newUser)
    }

    fun findUserBySessionId(sessionId: String): User? {
        return userRepository.findBySessionId(sessionId)
    }
}

