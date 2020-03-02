package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Qualifier("LocalDateSeeker")
class RemainingDaysOfYearSeeker(@Qualifier("LongSeeker") private val longSeekers: List<Seeker<Long>>) :
    Seeker<LocalDate> {
    override fun findMagic(data: LocalDate): List<RestController.Result> {
        val remainingDaysOfYear = (data.lengthOfYear() - data.dayOfYear).toLong()
        return longSeekers
            .flatMap { it.findMagic(remainingDaysOfYear) }
            .map {
                it.apply {
                    args["remainingDaysOfYear"] = remainingDaysOfYear
                }
            }
    }
}