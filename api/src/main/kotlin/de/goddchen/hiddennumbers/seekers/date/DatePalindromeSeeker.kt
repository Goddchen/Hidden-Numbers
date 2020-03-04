package de.goddchen.hiddennumbers.seekers.date

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
@Qualifier("LocalDateSeeker")
class DatePalindromeSeeker : Seeker<LocalDate> {
    override fun findMagic(data: LocalDate): List<RestController.Result> {
        val dateStringShortYear = data.format(DateTimeFormatter.ofPattern("yyMMdd"))
        val dateStringLongYear = data.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        return if (dateStringShortYear == dateStringShortYear.reversed() ||
            dateStringLongYear == dateStringLongYear.reversed()
        ) listOf(
            RestController.Result(
                name = "date-palindrome"
            )
        ) else emptyList()
    }
}