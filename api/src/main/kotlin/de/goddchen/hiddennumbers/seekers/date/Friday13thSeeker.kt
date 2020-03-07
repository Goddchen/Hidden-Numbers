package de.goddchen.hiddennumbers.seekers.date

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDate

@Component
@Qualifier("LocalDateSeeker")
class Friday13thSeeker : Seeker<LocalDate> {
    override fun findMagic(data: LocalDate) =
        if (data.dayOfWeek == DayOfWeek.FRIDAY && data.dayOfMonth == 13) {
            listOf(RestController.Result("friday-13th"))
        } else {
            emptyList()
        }
}