package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.WikiSearchActivity
import java.lang.ref.WeakReference

class WikiSearchActivityListenerViewModel(activity: WikiSearchActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGetButtonClicked() = mWeakReference.get()?.getButtonClicked()
    fun handleSaveButtonClicked() = mWeakReference.get()?.saveButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
    fun handleWikiSearchItemClicked(pos:Int) = mWeakReference.get()?.wikiSearchItemClicked(pos)
    fun handleQueryListButtonClicked() = mWeakReference.get()?.queryListButtonClicked()
}