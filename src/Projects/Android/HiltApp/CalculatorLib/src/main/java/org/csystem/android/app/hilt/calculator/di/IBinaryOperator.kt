package org.csystem.android.app.hilt.calculator.di

interface IBinaryOperator<T> {
    fun applyAsInt(a:T, b:T):T
    fun isValid(op:Char):Boolean
}