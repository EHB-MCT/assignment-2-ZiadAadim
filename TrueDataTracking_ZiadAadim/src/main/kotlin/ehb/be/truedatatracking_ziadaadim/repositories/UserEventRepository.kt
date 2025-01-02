package ehb.be.truedatatracking_ziadaadim.repositories

import ehb.be.truedatatracking_ziadaadim.models.UserEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEventRepository : MongoRepository<UserEvent, String>