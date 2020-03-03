package de.goddchen.hiddennumbers.seekers.date

import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Qualifier("LocalDateSeeker")
class DayOfYearSeeker(@Qualifier("LongSeeker") private val longSeekers: List<Seeker<Long>>) :
    Seeker<LocalDate> {
    override fun findMagic(data: LocalDate) =
        longSeekers.flatMap { it.findMagic(data.dayOfYear.toLong()) }
            .map { it.apply { args["dayOfYear"] = data.dayOfYear } }
}