package org.csystem.android.app.hiltapprepeat.viewmodel.listeners

import org.csystem.android.app.hiltapprepeat.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCalculateButton() = mWeakReference.get()?.calculateButtonClicked()
}