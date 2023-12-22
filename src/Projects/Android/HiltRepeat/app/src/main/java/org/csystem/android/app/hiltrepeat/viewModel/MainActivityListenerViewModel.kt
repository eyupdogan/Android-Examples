package org.csystem.android.app.hiltrepeat.viewModel

import org.csystem.android.app.hiltrepeat.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCalculateButton() = mWeakReference.get()?.calculateButtonClicked()
}