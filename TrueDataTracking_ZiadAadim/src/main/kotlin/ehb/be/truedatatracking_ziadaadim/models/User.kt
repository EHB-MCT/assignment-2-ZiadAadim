package ehb.be.truedatatracking_ziadaadim.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id val id: String? = null,
    val sessionId: String,
    val userId: String
)

