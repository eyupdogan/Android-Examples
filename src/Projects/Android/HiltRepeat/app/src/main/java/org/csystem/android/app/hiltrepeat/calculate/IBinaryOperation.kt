// IBinaryOperation
package org.csystem.android.app.hiltrepeat.calculate

interface IBinaryOperation<T>
{
    fun applyAsInt(a:T, b:T):T
    fun isValid(op:Char):Boolean
}