package de.goddchen.hiddennumbers

import de.goddchen.hiddennumbers.seekers.Seeker
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.subjects.PublishSubject
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import java.time.LocalDate
import java.util.concurrent.TimeUnit

@Suppress("unused")
@Controller
class GraphQLQueries(
        @Qualifier("LocalDateSeeker") private val localDateSeekers: List<Seeker<LocalDate>>
) :
        GraphQLQueryResolver {

    fun today(): List<GraphQLResult> =
            localDateSeekers.flatMap { it.findMagic(LocalDate.now()) }
                    .map { result ->
                        GraphQLResult(
                                name = result.name,
                                args = result.args.toMap()
                                        .map { entry -> GraphQLResult.KeyValuePair(key = entry.key, value = entry.value) }
                        )
                    }
}

@Suppress("unused")
@Controller
class GraphQLSubscriptions(
        @Qualifier("LocalDateSeeker") private val localDateSeekers: List<Seeker<LocalDate>>
) :
        GraphQLSubscriptionResolver {

    fun today(): Publisher<List<GraphQLResult>> =
            PublishSubject.interval(0, 10, TimeUnit.SECONDS)
                    .map {
                        localDateSeekers.flatMap { it.findMagic(LocalDate.now()) }
                                .map { result ->
                                    GraphQLResult(
                                            name = result.name,
                                            args = result.args.toMap()
                                                    .map { entry -> GraphQLResult.KeyValuePair(key = entry.key, value = entry.value) }
                                    )
                                }
                    }
                    .toFlowable(BackpressureStrategy.BUFFER)
}

data class GraphQLResult(val name: String, val args: List<KeyValuePair>) {
    data class KeyValuePair(val key: String, val value: Any)
}