package de.goddchen.hiddennumbers.seekers.number

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class OddNumbersSeeker : Seeker<Long> {
    override fun findMagic(data: Long) =
        if (data % 2 != 0L) listOf(
            RestController.Result(
                name = "odd"
            )
        ) else emptyList()
}