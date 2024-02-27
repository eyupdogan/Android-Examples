package org.csystem.android.app.hiltapprepeat.di.module.calculation

import javax.inject.Inject

class IMultiplyOperation @Inject constructor(): IBinaryOperation<Int>
{
    override fun applyAsInt(a: Int, b: Int): Int
    {
        return a * b
    }

    override fun isValid(op: Char): Boolean
    {
        return op == '*'
    }
}