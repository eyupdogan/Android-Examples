// IntAddOperation
package org.csystem.android.app.hiltrepeat.calculate

import javax.inject.Inject

class IntAddOperation @Inject constructor(): IBinaryOperation<Int>
{
    override fun applyAsInt(a: Int, b: Int): Int
    {
        return a + b
    }

    override fun isValid(op: Char): Boolean
    {
        return op == '+'
    }
}