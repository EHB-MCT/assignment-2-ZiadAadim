package ehb.be.truedatatracking_ziadaadim.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "user_events")
data class UserEvent(
    @Id
    val id: String? = null,
    val userId: String,
    val eventType: String,
    val page: String? = null,
    val buttonClicked: String? = null,
    val navigationPath: List<String>? = null,
    val timeSpent: Long? = null,
    val timeToAccept: Long? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
