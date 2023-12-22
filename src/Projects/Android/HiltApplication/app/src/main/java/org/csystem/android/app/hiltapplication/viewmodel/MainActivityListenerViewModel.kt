package org.csystem.android.app.hiltapplication.viewmodel

import org.csystem.android.app.hiltapplication.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCalculateButton() = mWeakReference.get()?.calculateButtonClicked()
}