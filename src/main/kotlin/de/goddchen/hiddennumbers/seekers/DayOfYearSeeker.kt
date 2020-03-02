package de.goddchen.hiddennumbers.seekers

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Qualifier("LocalDateSeeker")
class DayOfYearSeeker(@Qualifier("LongSeeker") private val longSeekers: List<Seeker<Int>>) : Seeker<LocalDate> {
    override fun findMagic(data: LocalDate) =
        longSeekers.flatMap { it.findMagic(data.dayOfYear) }
            .map { it.apply { args["dayOfYear"] = data.dayOfYear } }
}