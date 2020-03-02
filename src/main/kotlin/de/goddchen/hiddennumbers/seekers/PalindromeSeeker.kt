package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class PalindromeSeeker : Seeker<Long> {
    override fun findMagic(data: Long) =
        when {
            data.toString().length == 1 -> emptyList()
            data.toString().reversed() == data.toString() -> listOf(
                RestController.Result(
                    key = "palindrome"
                )
            )
            else -> emptyList()
        }
}