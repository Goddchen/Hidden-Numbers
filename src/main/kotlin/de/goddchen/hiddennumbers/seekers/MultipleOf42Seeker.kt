package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class MultipleOf42Seeker : Seeker<Long> {
    override fun findMagic(data: Long) = if (data % 42 == 0L) {
        listOf(RestController.Result(
            key = "multiple-of-42"
        ).apply { args["multiple"] = data / 42 })
    } else {
        emptyList()
    }
}