package de.goddchen.hiddennumbers.seekers.number

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class PrimeSeeker : Seeker<Long> {
    override fun findMagic(data: Long): List<RestController.Result> {
        for (i in 2..data / 2) {
            if (data % i == 0L) return emptyList()
        }
        return listOf(
            RestController.Result(
                name = "prime"
            )
        )
    }
}