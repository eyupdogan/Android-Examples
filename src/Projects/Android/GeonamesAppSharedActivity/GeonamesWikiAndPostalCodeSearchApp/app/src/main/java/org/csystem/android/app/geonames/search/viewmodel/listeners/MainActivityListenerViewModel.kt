package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleSettingsButtonClicked() = mWeakReference.get()?.settingsButtonClicked()
    fun handleWikiSearchButtonClicked() = mWeakReference.get()?.wikiSearchButtonClicked()
    fun handlePostalCodeSearchButtonClicked() = mWeakReference.get()?.postalCodeButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
}