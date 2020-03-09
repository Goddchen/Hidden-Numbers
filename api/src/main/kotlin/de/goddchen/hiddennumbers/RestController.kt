package de.goddchen.hiddennumbers

import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/")
class RestController(
    @Qualifier("LongSeeker") private val longSeekers: List<Seeker<Long>>,
    @Qualifier("LocalDateSeeker") private val localDateSeekers: List<Seeker<LocalDate>>
) {

    @GetMapping("/number/{number}")
    fun number(
        @PathVariable("number")
        number: Long
    ) = longSeekers.flatMap { it.findMagic(number) }

    @GetMapping("/date/{date}")
    fun date(
        @PathVariable("date")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        date: LocalDate
    ) = localDateSeekers.flatMap { it.findMagic(date) }

    @GetMapping("/date/today")
    fun today() = localDateSeekers.flatMap { it.findMagic(LocalDate.now()) }

    @GetMapping("/date-stats")
    fun dateStats(
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) from: LocalDate?,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) to: LocalDate?,
        @RequestParam("limit") limit: Int?
    ): Map<*, *> {
        val fromDate = from ?: LocalDate.now().withDayOfYear(1).withDayOfMonth(1)
        val toDate = to ?: fromDate.plusYears(1).minusDays(1)
        var date = fromDate
        val stats = mutableListOf<Stat<LocalDate>>()
        do {
            stats.add(
                Stat(
                    data = date,
                    results = localDateSeekers.flatMap { it.findMagic(date) },
                    url = "https://hidden-numbers.goddchen.de/date/${date.format(DateTimeFormatter.ISO_DATE)}"
                )
            )
            date = date.plusDays(1)
        } while (date != toDate)
        return stats
            .sortedByDescending { it.results.size }
            .take(limit ?: Int.MAX_VALUE)
            .map { it.data to mapOf("number" to it.results.size, "url" to it.url) }
            .toMap()
    }

    @GetMapping("/number-stats")
    fun numberStats(
        @RequestParam("from") from: Long?,
        @RequestParam("to") to: Long?,
        @RequestParam("limit") limit: Int?
    ): Map<*, *> {
        val fromNumber = from ?: 1
        val toNumber = to ?: 1000
        var number = fromNumber
        val stats = mutableListOf<Stat<Long>>()
        do {
            stats.add(
                Stat(
                    data = number,
                    results = longSeekers.flatMap { it.findMagic(number) },
                    url = "https://hidden-numbers.goddchen.de/number/$number"
                )
            )
            number += 1
        } while (number != toNumber)
        return stats
            .sortedByDescending { it.results.size }
            .take(limit ?: Int.MAX_VALUE)
            .map { it.data to mapOf("number" to it.results.size, "url" to it.url) }
            .toMap()
    }

    data class Result(val name: String, val args: MutableMap<String, Any> = mutableMapOf())

    data class Stat<T>(val data: T, val results: List<Result>, val url: String)

}

