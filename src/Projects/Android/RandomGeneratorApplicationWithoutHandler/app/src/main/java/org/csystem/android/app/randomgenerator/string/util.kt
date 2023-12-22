package org.csystem.android.app.randomgenerator.string

import kotlin.random.Random

fun generateRandomTextEN(count: Int, random: Random = Random): String
{
    val sb = StringBuilder(count)

    (1..count).forEach {sb.append((if (random.nextBoolean()) "A" else "a") + random.nextInt(26)) }

    return sb.toString()
}