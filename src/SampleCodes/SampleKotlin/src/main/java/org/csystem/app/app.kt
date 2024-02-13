/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Parametresi ile aldığı Int türden bir sayının tersini döndüren reversed isimli fonksiyonu yazınız
    ve aşağıdaki kod ile test ediniz.
    Algoritma: 123 -> 3 -> 3 * 10 + 2 = 32 -> 32 * 10 + 1 = 321
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main() = runIsPalindromeTest()

fun runIsPalindromeTest()
{
    while (true) {
        print("Bir sayı giriniz:")
        val value = readln().toInt()

        println(if (isPalindrome(value)) "$value palindromdur" else "$value palindrom değildir")

        if (value == 0)
            break
    }

    println("Tekrar yapıyor musunuz?")
}


fun isPalindrome(value: Int):Boolean
{
    return reversed(value) == value
}

fun reversed(value:Int):Int
{
    var temp = value
    var result = 0

    while (temp != 0) {
        result = result * 10 + temp % 10
        temp /= 10
    }

    return result
}