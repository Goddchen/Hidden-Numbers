package de.goddchen.hiddennumbers.seekers.number

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class DigitSumMetaSeeker(@Qualifier("LongSeeker") private val longSeekers: List<Seeker<Long>>) :
    Seeker<Long> {
    override fun findMagic(data: Long): List<RestController.Result> {
        val digitSum = data.toString().sumBy { char -> char.toString().toInt() }
        return longSeekers
            //.filter { it !is DigitSumMetaSeeker }
            .flatMap { it.findMagic(digitSum.toLong()) }
            .map {
                it.apply {
                    args["digitSum"] = digitSum
                }
            }
    }
}