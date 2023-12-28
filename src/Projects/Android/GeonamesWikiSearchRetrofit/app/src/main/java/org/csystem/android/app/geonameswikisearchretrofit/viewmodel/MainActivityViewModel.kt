package org.csystem.android.app.geonameswikisearchretrofit.viewmodel

import org.csystem.android.app.geonameswikisearchretrofit.MainActivity
import java.lang.ref.WeakReference

class MainActivityViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGetButtonClicked() = mWeakReference.get()?.getButtonClicked()
}