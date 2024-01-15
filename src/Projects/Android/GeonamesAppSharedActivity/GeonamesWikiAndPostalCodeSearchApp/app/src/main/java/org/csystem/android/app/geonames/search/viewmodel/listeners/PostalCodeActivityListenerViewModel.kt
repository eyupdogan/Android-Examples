package org.csystem.android.app.geonames.search.viewmodel.listeners

import org.csystem.android.app.geonames.search.PostalCodeActivity
import java.lang.ref.WeakReference

class PostalCodeActivityListenerViewModel(activity: PostalCodeActivity)
{
    private val mWeakReference = WeakReference(activity)


    fun handleGetButtonClicked() = mWeakReference.get()?.getButtonClicked()
    fun handlePostalCodeItemClicked(pos:Int) = mWeakReference.get()?.postalCodeItemClicked(pos)
    fun handleSaveButtonClicked() = mWeakReference.get()?.saveButtonClicked()
    fun handleCloseButtonClicked() = mWeakReference.get()?.closeButtonClicked()
    fun handleQueryListButtonClicked() = mWeakReference.get()?.queryListButtonClicked()
}