package ehb.be.truedatatracking_ziadaadim.repositories

import ehb.be.truedatatracking_ziadaadim.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findBySessionId(sessionId: String): User?
}
