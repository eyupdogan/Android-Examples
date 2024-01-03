package org.csystem.android.app.wikisearchrepeat.viewmodel

import org.csystem.android.app.wikisearchrepeat.MainActivity
import java.lang.ref.WeakReference

class MainActivityListenerViewModel(activity: MainActivity)
{
    private val mWeakReference = WeakReference(activity)

    fun handleGetButtonClicked() = mWeakReference.get()?.getButtonClicked()

    fun handleListViewItemClicked(pos:Int) = mWeakReference.get()?.listViewItemClicked(pos)
    fun handleSaveButtonClicked() = mWeakReference.get()?.saveButtonClicked()
}