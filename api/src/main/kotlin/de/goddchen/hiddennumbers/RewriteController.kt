package de.goddchen.hiddennumbers

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDate

@Controller
class RewriteController {

    @GetMapping("number/{number}")
    fun number(@PathVariable("number") number: Long) = "forward:/?number=$number"

    @GetMapping("date/{date}")
    fun date(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @PathVariable("date") date: LocalDate
    ) =
        "forward:/?date=$date"
}