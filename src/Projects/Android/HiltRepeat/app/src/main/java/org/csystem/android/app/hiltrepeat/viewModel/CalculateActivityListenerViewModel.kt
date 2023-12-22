package org.csystem.android.app.hiltrepeat.viewModel

import org.csystem.android.app.hiltrepeat.CalculateActivity
import java.lang.ref.WeakReference

class CalculateActivityListenerViewModel(activity: CalculateActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCalculateButton() = mWeakReference.get()?.calculateButtonClicked()
}