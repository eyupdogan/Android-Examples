package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.SettingsActivity
import java.lang.ref.WeakReference

class SettingsActivityListenerViewModel(activity: SettingsActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleSaveAndBackButtonClicked() = mWeakReference.get()?.saveAndBack()
}