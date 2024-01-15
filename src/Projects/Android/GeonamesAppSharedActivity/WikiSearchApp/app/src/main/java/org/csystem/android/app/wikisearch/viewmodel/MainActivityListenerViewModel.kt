package org.csystem.android.app.wikisearch.viewmodel

import org.csystem.android.app.wikisearch.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleSearchButtonClicked() = mWeakReference.get()?.searchButtonClicked()

}