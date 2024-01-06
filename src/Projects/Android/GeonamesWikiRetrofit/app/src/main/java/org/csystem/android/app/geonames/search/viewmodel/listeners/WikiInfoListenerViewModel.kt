package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.WikiInfoActivity
import java.lang.ref.WeakReference

class WikiInfoListenerViewModel(activity: WikiInfoActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCloseButtonClicked() = mWeakReference.get()?.finish()
}