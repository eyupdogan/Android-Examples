package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.QueryListActivity
import java.lang.ref.WeakReference

class QueryListActivityListenerViewModel(activity: QueryListActivity)
{
    private val mWeakReference = WeakReference(activity)
    fun handleCloseButtonClicked() = mWeakReference.get()?.finish()
}