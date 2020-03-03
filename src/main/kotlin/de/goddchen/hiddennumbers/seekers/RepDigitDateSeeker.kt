package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
@Qualifier("LocalDateSeeker")
class RepDigitDateSeeker : Seeker<LocalDate> {
    override fun findMagic(data: LocalDate): List<RestController.Result> {
        val dateStringShortYear = data.format(DateTimeFormatter.ofPattern("yyMMdd"))
        val dateStringLongYear = data.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        return if (dateStringShortYear.asIterable().distinct().size == 1 ||
            dateStringLongYear.asIterable().distinct().size == 1
        )
            listOf(RestController.Result(key = "repdigitdate"))
        else emptyList()
    }
}