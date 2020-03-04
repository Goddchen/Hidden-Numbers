package de.goddchen.hiddennumbers.seekers

import de.goddchen.hiddennumbers.RestController

interface Seeker<T> {
    fun findMagic(data: T): List<RestController.Result>
}