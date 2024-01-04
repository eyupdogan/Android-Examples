package org.csystem.android.app.search.viewmodel

import org.csystem.android.app.search.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGetButtonClicked() = mWeakReference.get()?.getButtonClicked()
}