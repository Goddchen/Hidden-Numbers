package de.goddchen.hiddennumbers

import de.goddchen.hiddennumbers.seekers.Seeker
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import java.time.LocalDate

@Suppress("unused")
@Controller
class GraphQLResolvers(
        @Qualifier("LocalDateSeeker") private val localDateSeekers: List<Seeker<LocalDate>>
) : GraphQLQueryResolver {

    fun today(): List<GraphQLResult> =
            localDateSeekers.flatMap { it.findMagic(LocalDate.now()) }
                    .map { result ->
                        GraphQLResult(
                                name = result.name,
                                args = result.args.toList()
                                        .map { arg -> GraphQLResult.KeyValuePair(key = arg.first, value = arg.second) }
                        )
                    }

}

data class GraphQLResult(val name: String, val args: List<KeyValuePair>) {
    data class KeyValuePair(val key: String, val value: Any)
}