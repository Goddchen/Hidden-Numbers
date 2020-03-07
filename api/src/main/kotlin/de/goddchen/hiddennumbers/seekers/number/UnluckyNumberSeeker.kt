package de.goddchen.hiddennumbers.seekers.number

import de.goddchen.hiddennumbers.RestController
import de.goddchen.hiddennumbers.seekers.Seeker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("LongSeeker")
class UnluckyNumberSeeker : Seeker<Long> {
    override fun findMagic(data: Long): List<RestController.Result> {
        val results = mutableListOf<RestController.Result>()
        if (data == 13L) results.add(RestController.Result("unlucky-number")
            .apply { args["origin"] = "Western Europe" })
        if (data.toString().contains("4")) results.add(RestController.Result("unlucky-number")
            .apply {
                args["origin"] = "China, Japan"
                args["reason"] = "Contains 4"
            })
        if (data.toString().contains("39")) results.add(RestController.Result("unlucky-number")
            .apply {
                args["origin"] = "Afghanistan"
                args["reason"] = "Contains 39"
            })
        if (data == 17L) results.add(RestController.Result("unlucky-number")
            .apply { args["origin"] = "Italy, Brazil" })
        return results
    }
}