package de.goddchen.hiddennumbers.seekers.number

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class RepDigitSeeker : Seeker<Long> {
    override fun findMagic(data: Long) =
        when {
            data.toString().length == 1 -> emptyList()
            data.toString().all { it == data.toString()[0] } -> listOf(
                RestController.Result(
                    name = "repdigit"
                )
            )
            else -> emptyList()
        }
}