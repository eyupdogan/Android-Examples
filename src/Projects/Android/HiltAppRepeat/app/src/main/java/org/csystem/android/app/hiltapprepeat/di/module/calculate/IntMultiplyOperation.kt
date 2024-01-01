package org.csystem.android.app.hiltapprepeat.di.module.calculate

import javax.inject.Inject

class IntMultiplyOperation @Inject constructor():IBinaryOperation<Int>
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