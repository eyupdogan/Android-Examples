package org.csystem.android.app.wiki.search.viewmodel.listeners

import org.csystem.android.app.wiki.search.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGetButtonClicked() = mWeakReference.get()?.finish()
}