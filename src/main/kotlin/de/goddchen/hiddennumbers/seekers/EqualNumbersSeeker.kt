package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class EqualNumbersSeeker : Seeker<Long> {
    override fun findMagic(data: Long) =
        if (data % 2 == 0L) listOf(
            RestController.Result(
                key = "equal"
            )
        ) else emptyList()
}