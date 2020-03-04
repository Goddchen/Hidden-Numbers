package de.goddchen.hiddennumbers

import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
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

    data class Result(val name: String, val args: MutableMap<String, Any> = mutableMapOf())

}

