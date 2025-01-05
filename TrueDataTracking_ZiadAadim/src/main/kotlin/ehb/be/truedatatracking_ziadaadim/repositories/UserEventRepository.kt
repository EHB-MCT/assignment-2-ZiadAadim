package ehb.be.truedatatracking_ziadaadim.repositories

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserEventRepository : MongoRepository<UserEvent, String> {

    @Query("{ 'userId': ?0 }")
    fun findAllByUserId(userId: String): List<UserEvent>

    @Query("{ }")
    fun findAllEvents(): List<UserEvent>
}