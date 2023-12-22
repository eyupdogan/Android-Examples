package org.csystem.android.app.displaydatetime.viewmodel

import org.csystem.android.app.displaydatetime.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(val activity: MainActivity)
{
    val mWeakReference = WeakReference(activity)

    fun handleCloseButton() = mWeakReference.get()?.closeButtonClicked()
}