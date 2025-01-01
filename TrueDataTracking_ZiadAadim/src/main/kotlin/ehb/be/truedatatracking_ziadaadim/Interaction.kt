package ehb.be.truedatatracking_ziadaadim

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Interaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val action: String,
    val module: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
