package org.csystem.android.app.hiltapprepeat.di.module.calculation

interface IBinaryOperation<T>
{
    fun applyAsInt(a:T, b:T):T
    fun isValid(op:Char):Boolean
}