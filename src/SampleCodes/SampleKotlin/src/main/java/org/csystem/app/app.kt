
package org.csystem.app

import kotlin.math.pow


fun main()
{
    println(isPrime(4935060337471977161))

}

fun isPrime(value:Long, n:Int):Boolean
{
    if (value % n == 0L)
        return false

    if (n * n > value)
        return true

    return isPrime(value, n + 2)
}
fun isPrime(value:Long):Boolean
{
    if (value % 2 == 0L)
        return value == 2L

    if (value % 3 == 0L)
        return value == 3L

    if (value % 5 == 0L)
        return value == 5L

    if (value % 7 == 0L)
        return value == 7L

    if (value % 11 == 0L)
        return value == 11L

    return isPrime(value, 11)
}
