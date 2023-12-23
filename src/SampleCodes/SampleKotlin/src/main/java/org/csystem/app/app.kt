/*----------------------------------------------------------------------------------------------------------------------
    İki tane iki tırnak karakteri arasında ters bölü karakterinin kullanımı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val value = readln().toInt()
    val result = sumDigits3(value)
    println(result)
}


fun sumDigits3(a:Int):Int
{
    var temp = a
    var sum = 0
    while(temp != 0){
        sum += temp % 10
        temp /= 10
    }
    return sum
}
