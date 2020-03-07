package de.goddchen.hiddennumbers.seekers.date

import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Qualifier("LocalDateSeeker")
class DayOfMonthSeeker(@Qualifier("LongSeeker") private val longSeekers: List<Seeker<Long>>) :
    Seeker<LocalDate> {
    override fun findMagic(data: LocalDate) =
        longSeekers.flatMap { it.findMagic(data.dayOfMonth.toLong()) }
            .map { it.apply { args["dayOfMonth"] = data.dayOfMonth } }
}