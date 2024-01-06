package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.PostalCodeInfoActivity
import java.lang.ref.WeakReference

class PostalCodeInfoListenerViewModel(activity: PostalCodeInfoActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleCloseButtonClicked() = mWeakReference.get()?.finish()
}